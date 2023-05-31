package com.brandwatch.shop.domain.repository;

import com.brandwatch.shop.domain.entity.Order;
import com.brandwatch.shop.domain.entity.ProductOrder;
import com.brandwatch.shop.domain.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByStatus(OrderStatus status);

    List<ProductOrder> findDistinctProductsByStatus(OrderStatus status);

    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.status = :status WHERE o.id IN :id")
    void updateStatusByIdIn(OrderStatus status, List<Long> id);
}
