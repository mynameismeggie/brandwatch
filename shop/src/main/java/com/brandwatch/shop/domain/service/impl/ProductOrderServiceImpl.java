package com.brandwatch.shop.domain.service.impl;

import com.brandwatch.shop.domain.entity.ProductOrder;
import com.brandwatch.shop.domain.repository.ProductOrderRepository;
import com.brandwatch.shop.domain.service.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductOrderServiceImpl implements ProductOrderService {
    private final ProductOrderRepository repository;

    @Override
    public Optional<ProductOrder> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must be not null when finding by id");
        }
        return repository.findById(id);
    }

    @Override
    public ProductOrder create(ProductOrder productOrder) {
        if (productOrder == null || productOrder.id() != null) {
            throw new IllegalArgumentException("Id must be null when creating product order");
        }
        return repository.save(productOrder);
    }

    @Override
    public ProductOrder update(ProductOrder productOrder) {
        if (productOrder == null || productOrder.id() == null) {
            throw new IllegalArgumentException("Id must be not null when updating productOrder");
        }
        return repository.save(productOrder);
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            repository.deleteById(id);
        }
    }
}
