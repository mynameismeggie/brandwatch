package com.brandwatch.store.ingress.facade.impl;

import com.brandwatch.store.domain.entity.Product;
import com.brandwatch.store.domain.service.ProductService;
import com.brandwatch.store.egress.producer.ProductMessageProducer;
import com.brandwatch.store.ingress.consumer.ProductMessageConsumer;
import com.brandwatch.store.ingress.facade.ProductFacade;
import com.brandwatch.store.ingress.request.LoadProductRequest;
import com.brandwatch.store.ingress.response.OrderResponse;
import com.brandwatch.store.ingress.response.ProductOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductFacadeImpl implements ProductFacade {
    private final ProductService productService;
    private final ProductMessageConsumer productMessageConsumer;
    private final ProductMessageProducer productMessageProducer;

    @Override
    public List<ProductOrderResponse> getMissingProducts() {
        final var pendingProducts = productMessageProducer.getPendingProducts();
        final var productDbMap = productService.findAllAsMap();
        final var missingProducts = new ArrayList<ProductOrderResponse>();

        pendingProducts.forEach(pendingProduct -> {
            final var productFromDb = productDbMap.get(pendingProduct.id());
            final var missingProduct = ProductOrderResponse.builder()
                    .id(productFromDb.id())
                    .requiredQuantity(pendingProduct.requiredQuantity() - productFromDb.quantity())
                    .build();

            missingProducts.add(missingProduct);
        });

        return missingProducts;
    }

    @Override
    public void loadProducts(List<LoadProductRequest> loadProductRequests) {
        final var pendingOrders = productMessageProducer.getPendingOrders();
        final var productDbMap = productService.findAllAsMap();
        loadProductRequests.forEach(productRequest -> addProductQuantities(productRequest, productDbMap));

        final var successfulOrders = new ArrayList<OrderResponse>();
        pendingOrders.forEach(pendingOrder -> {
            if (isOrderCompletable(pendingOrder, productDbMap)) {
                removeOrderQuantities(pendingOrder, productDbMap);
                successfulOrders.add(pendingOrder);
            }
        });

        productService.updateAll(productDbMap.values());
        productMessageProducer.sendSuccessfulOrders(successfulOrders);

    }

    private boolean isOrderCompletable(OrderResponse order, Map<UUID, Product> productMap) {
        return order.products()
                .stream()
                .allMatch(product -> productMap.get(product.id()).quantity() >= product.requiredQuantity());
    }

    private void addProductQuantities(LoadProductRequest productRequest, Map<UUID, Product> productMap) {
        productMap.computeIfPresent(productRequest.id(),
                (key, value) -> value.toBuilder()
                        .quantity((value.quantity() + productRequest.quantity()))
                        .build());
    }

    private void removeOrderQuantities(OrderResponse order, Map<UUID, Product> productMap) {
        order.products().forEach(product -> productMap.computeIfPresent(product.id(),
                (key, value) -> value.toBuilder()
                        .quantity((value.quantity() - product.requiredQuantity()))
                        .build()));
    }
}
