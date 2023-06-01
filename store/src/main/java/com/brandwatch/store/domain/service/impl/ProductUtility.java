package com.brandwatch.store.domain.service.impl;

import com.brandwatch.store.domain.entity.Product;

import java.util.List;
import java.util.Map;

/**
 * Utility methods for working with products
 */
public class ProductUtility {
    /**
     * Checks if the products quality is enough to complete the order
     *
     * @param products   accepts list of products
     * @param productMap accepts map of product id and product
     * @return true - if the order can be completed <br>false - if the order cannot be completed
     */
    public static boolean isOrderCompletable(List<Product> products, Map<Long, Product> productMap) {
        return products.stream()
                .allMatch(product -> productMap.get(product.id()).quantity() >= product.quantity());
    }

    /**
     * Adds new product quantities if the products are present
     *
     * @param products   accepts list of Product entity
     * @param productMap accepts map of Product id and Product entity
     */
    public static void addProductQuantities(List<Product> products, Map<Long, Product> productMap) {
        products.forEach(product -> addProductQuantity(product, productMap));
    }

    /**
     * Adds new product quantity if the product is present
     *
     * @param product    accepts Product entity
     * @param productMap accepts map of Product id and Product entity
     */
    public static void addProductQuantity(Product product, Map<Long, Product> productMap) {
        productMap.computeIfPresent(product.id(), (key, value) -> value.toBuilder()
                .quantity((value.quantity() + product.quantity()))
                .build());
    }

    /**
     * Removes products quantities if the products are present
     *
     * @param products   accepts list of Product entity
     * @param productMap accepts map of Product id and Product entity
     */
    public static void removeProductQuantities(List<Product> products, Map<Long, Product> productMap) {
        products.forEach(product -> removeProductQuantity(product, productMap));
    }

    /**
     * Removes product quantity if the product is present
     *
     * @param product    accepts Product entity
     * @param productMap accepts map of Product id and Product entity
     */
    public static void removeProductQuantity(Product product, Map<Long, Product> productMap) {
        productMap.computeIfPresent(product.id(), (key, value) -> value.toBuilder()
                .quantity((value.quantity() - product.quantity()))
                .build());
    }
}
