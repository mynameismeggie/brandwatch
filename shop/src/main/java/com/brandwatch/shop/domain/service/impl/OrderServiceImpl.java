package com.brandwatch.shop.domain.service.impl;

import com.brandwatch.shop.domain.entity.Order;
import com.brandwatch.shop.domain.entity.ProductOrder;
import com.brandwatch.shop.domain.enums.OrderStatus;
import com.brandwatch.shop.domain.repository.OrderRepository;
import com.brandwatch.shop.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllByStatus(OrderStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status must be not null when finding by status");
        }
        return orderRepository.findAllByStatus(status);
    }

    @Override
    public List<ProductOrder> findDistinctProductsByStatus(OrderStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status must be not null when finding by status");
        }
        return orderRepository.findAllByStatus(status)
                .stream()
                .flatMap(order -> order.products().stream())
                .collect(groupingBy(ProductOrder::productId, summingLong(ProductOrder::quantity)))
                .entrySet().stream()
                .map(entry -> ProductOrder.builder().productId(entry.getKey()).quantity(entry.getValue()).build())
                .collect(toList());
    }

    @Override
    public Optional<Order> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must be not null when finding by id");
        }
        return orderRepository.findById(id);
    }

    @Override
    public Order create(Order order) {
        if (order == null || order.id() != null) {
            throw new IllegalArgumentException("Id must be null when creating order");
        }
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        if (order == null || order.id() == null) {
            throw new IllegalArgumentException("Id must be not null when updating order");
        }
        return orderRepository.save(order);
    }

    @Override
    public void updateStatusByIds(List<Long> ids, OrderStatus status) {
        if (ids == null || status == null) {
            throw new IllegalArgumentException("Id and status must be not null when updating orders");
        }
        orderRepository.updateStatusByIdIn(status, ids);
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            orderRepository.deleteById(id);
        }
    }
}
