package com.brandwatch.store.domain.service;

import com.brandwatch.store.domain.entity.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    List<Product> findAll();

    Optional<Product> findById(UUID id);

    Map<UUID, Product> findAllAsMap();

    Product create(Product product);

    List<Product> updateAll(Iterable<Product> products);

    Product update(Product product);

    void deleteById(UUID id);
}
