package com.brandwatch.shop.domain.service;

import com.brandwatch.shop.domain.entity.Order;
import com.brandwatch.shop.domain.entity.ProductOrder;
import com.brandwatch.shop.domain.enums.OrderStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    List<Order> findAll();

    List<Order> findAllByStatus(OrderStatus status);
    List<ProductOrder> findDistinctProductsByStatus(OrderStatus status);

    Optional<Order> findById(UUID id);

    Order create(Order order);

    Order update(Order order);

    void updateStatusByIds(List<UUID> ids, OrderStatus status);

    void deleteById(UUID id);
}
