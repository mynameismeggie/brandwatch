package com.brandwatch.store.domain.service.impl;

import com.brandwatch.store.domain.entity.Product;
import com.brandwatch.store.domain.service.ProductHelperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductHelperServiceImpl implements ProductHelperService {
    @Override
    public boolean isOrderCompletable(List<Product> products, Map<Long, Product> productMap) {
        return products.stream()
                .allMatch(product -> productMap.get(product.id()).quantity() >= product.quantity());
    }

    @Override
    public void addProductQuantities(List<Product> products, Map<Long, Product> productMap) {
        products.forEach(product -> addProductQuantity(product, productMap));
    }

    @Override
    public void addProductQuantity(Product product, Map<Long, Product> productMap) {
        productMap.computeIfPresent(product.id(), (key, value) -> value.toBuilder()
                .quantity((value.quantity() + product.quantity()))
                .build());
    }

    @Override
    public void removeProductQuantities(List<Product> products, Map<Long, Product> productMap) {
        products.forEach(product -> removeProductQuantity(product, productMap));
    }

    @Override
    public void removeProductQuantity(Product product, Map<Long, Product> productMap) {
        productMap.computeIfPresent(product.id(), (key, value) -> value.toBuilder()
                .quantity((value.quantity() - product.quantity()))
                .build());
    }
}
