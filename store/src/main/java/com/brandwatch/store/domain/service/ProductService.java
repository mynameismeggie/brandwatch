package com.brandwatch.store.domain.service;

import com.brandwatch.store.domain.entity.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    Map<Long, Product> findAllByIdIn(List<Long> ids);

    Map<Long, Product> findAllAsMap();

    Product create(Product product);

    List<Product> updateAll(Iterable<Product> products);

    Product update(Product product);

    void deleteById(Long id);
}
