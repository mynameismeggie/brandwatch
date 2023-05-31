package com.brandwatch.shop.domain.service;

import com.brandwatch.shop.domain.entity.ProductOrder;

import java.util.Optional;

/**
 * Used for serving business logic for the ProductOrder Entity
 */
public interface ProductOrderService {

    /**
     * Finds product order by id
     *
     * @param id accepts Long
     * @return Optional of product order
     */
    Optional<ProductOrder> findById(Long id);

    /**
     * Creates product order
     *
     * @param productOrder accepts ProductOrder entity
     * @return created ProductOrder entity
     */
    ProductOrder create(ProductOrder productOrder);

    /**
     * Updates product order
     *
     * @param productOrder accepts ProductOrder entity
     * @return updated ProductOrder entity
     */
    ProductOrder update(ProductOrder productOrder);

    /**
     * Deletes product order by id
     *
     * @param id accepts ProductOrder id
     */
    void deleteById(Long id);
}
