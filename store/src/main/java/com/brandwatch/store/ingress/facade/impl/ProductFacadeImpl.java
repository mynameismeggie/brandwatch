package com.brandwatch.store.ingress.facade.impl;

import com.brandwatch.store.domain.service.ProductHelperService;
import com.brandwatch.store.domain.service.ProductService;
import com.brandwatch.store.egress.producer.ProductMessageProducer;
import com.brandwatch.store.ingress.facade.ProductFacade;
import com.brandwatch.store.ingress.mapper.OrderMapper;
import com.brandwatch.store.ingress.mapper.ProductMapper;
import com.brandwatch.store.ingress.request.OrderRequest;
import com.brandwatch.store.ingress.request.ProductRequest;
import com.brandwatch.store.ingress.response.OrderResponse;
import com.brandwatch.store.ingress.response.ProductOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductFacadeImpl implements ProductFacade {
    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;
    private final ProductService productService;
    private final ProductMessageProducer productMessageProducer;
    private final ProductHelperService productHelperService;

    @Override
    public List<ProductOrderResponse> getMissingProducts() {
        final var pendingProducts = productMessageProducer.getPendingProducts();
        final var productDbMap = productService.findAllAsMap();
        final var missingProducts = new ArrayList<ProductOrderResponse>();

        pendingProducts.forEach(pendingProduct -> {
            final var productFromDb = productDbMap.get(pendingProduct.id());
            final var missingProduct = ProductOrderResponse.builder()
                    .id(productFromDb.id())
                    .quantity(pendingProduct.quantity() - productFromDb.quantity())
                    .build();

            missingProducts.add(missingProduct);
        });

        return missingProducts;
    }

    @Override
    public void loadProducts(List<ProductRequest> productRequests) {
        final var pendingOrders = productMessageProducer.getPendingOrders();
        final var productDbMap = productService.findAllAsMap();

        productHelperService.addProductQuantities(productMapper.toEntityFromRequest(productRequests), productDbMap);

        final var successfulOrders = new ArrayList<OrderResponse>();
        pendingOrders.forEach(pendingOrder -> {
            final var pendingProducts = productMapper.toEntityFromResponse(pendingOrder.products());
            if (productHelperService.isOrderCompletable(pendingProducts, productDbMap)) {
                productHelperService.removeProductQuantities(pendingProducts, productDbMap);
                successfulOrders.add(pendingOrder);
            }
        });

        productService.updateAll(productDbMap.values());
        if (!successfulOrders.isEmpty()) {
            productMessageProducer.sendSuccessfulOrders(successfulOrders);
        }
    }

    @Override
    public void handleNewOrder(OrderRequest request) {
        final var productIds = request.products()
                .stream()
                .map(ProductRequest::id)
                .toList();

        final var productDbMap = productService.findAllByIdIn(productIds);
        final var newProducts = productMapper.toEntityFromRequest(request.products());
        if (productHelperService.isOrderCompletable(newProducts, productDbMap)) {
            productHelperService.removeProductQuantities(newProducts, productDbMap);
            productService.updateAll(productDbMap.values());
            final var successfulOrder = orderMapper.toOrderResponse(request);
            productMessageProducer.sendSuccessfulOrders(List.of(successfulOrder));
        }
    }
}
