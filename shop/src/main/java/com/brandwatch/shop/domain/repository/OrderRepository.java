package com.brandwatch.shop.domain.repository;

import com.brandwatch.shop.domain.entity.Order;
import com.brandwatch.shop.domain.entity.ProductOrder;
import com.brandwatch.shop.domain.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Transactional
    void updateStatusByIdIn(List<UUID> ids, OrderStatus status);

    List<Order> findAllByStatus(OrderStatus status);

    List<ProductOrder> findDistinctProductsByStatus(OrderStatus status);
}
