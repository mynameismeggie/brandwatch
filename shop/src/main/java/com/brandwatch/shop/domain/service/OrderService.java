package com.brandwatch.shop.domain.service;

import com.brandwatch.shop.domain.entity.Order;
import com.brandwatch.shop.domain.entity.ProductOrder;
import com.brandwatch.shop.domain.enums.OrderStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Used for serving business logic for the Order Entity
 */
public interface OrderService {
    /**
     * Finds all orders
     * @return list of all orders
     */
    List<Order> findAll();

    /**
     * Finds all orders with requested status
     * @param status accepts OrderStatus
     * @return list of orders
     */
    List<Order> findAllByStatus(OrderStatus status);

    /**
     * Finds distinct products with requested status
     * @param status accepts OrderStatus
     * @return list of ProductOrder which contains product name and quantity
     */
    List<ProductOrder> findDistinctProductsByStatus(OrderStatus status);

    /**
     * Finds order by id
     * @param id accepts UUID
     * @return Optional of order
     */
    Optional<Order> findById(UUID id);

    /**
     * Creates order
     * @param order accepts Order entity
     * @return Order entity
     */
    Order create(Order order);

    /**
     * Updates order
     * @param order accepts Order entity
     * @return Order entity
     */
    Order update(Order order);

    /**
     * Updates order statuses by requested order ids and status
     * @param ids accepts list of order ids
     * @param status accepts OrderStatus
     */
    void updateStatusByIds(List<UUID> ids, OrderStatus status);

    /**
     * Deletes order by requested id
     * @param id accepts order id
     */
    void deleteById(UUID id);
}
