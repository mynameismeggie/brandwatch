package com.brandwatch.store.domain.service;

import com.brandwatch.store.domain.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductHelperService {
    boolean isOrderCompletable(List<Product> products, Map<Long, Product> productMap);

    void addProductQuantities(List<Product> products, Map<Long, Product> productMap);

    void addProductQuantity(Product product, Map<Long, Product> productMap);

    void removeProductQuantities(List<Product> products, Map<Long, Product> productMap);

    void removeProductQuantity(Product product, Map<Long, Product> productMap);
}
