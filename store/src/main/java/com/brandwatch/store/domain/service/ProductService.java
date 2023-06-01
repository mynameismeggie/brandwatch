package com.brandwatch.store.domain.service;

import com.brandwatch.store.domain.entity.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;
/**
 * Used for serving business logic for the Product Entity
 */
public interface ProductService {
    /**
     * Finds all products
     * @return list of all products
     */
    List<Product> findAll();

    /**
     * Finds a product by requested id
     * @param id accepts product id
     * @return returns Optional of Product entity
     */
    Optional<Product> findById(Long id);

    /**
     * Finds all the products by requested ids
     * @param ids accepts list of product ids
     * @return Map of product id and Product entity
     */
    Map<Long, Product> findAllByIdIn(List<Long> ids);

    /**
     * Finds all products
     * @return Map of product id and Product entity
     */
    Map<Long, Product> findAllAsMap();

    /**
     * Creates new product
     * @param product accepts Product entity
     * @return returns created Product entity
     */
    Product create(Product product);

    /**
     * Updates all passed products
     * @param products accepts Iterable of Product entity to be updated
     * @return list of updated Product entities
     */
    List<Product> updateAll(Iterable<Product> products);

    /**
     * Updates single product
     * @param product accepts Product entity
     * @return updated Product entity
     */
    Product update(Product product);

    /**
     * Deletes product by id
     * @param id accepts product id
     */
    void deleteById(Long id);
}
