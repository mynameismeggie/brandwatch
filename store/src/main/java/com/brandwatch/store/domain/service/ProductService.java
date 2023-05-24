package com.brandwatch.store.domain.service;

import com.brandwatch.store.domain.entity.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    Optional<Product> findById(UUID id);

    Product create(Product product);

    Product update(Product product);

    void deleteById(UUID id);
}
