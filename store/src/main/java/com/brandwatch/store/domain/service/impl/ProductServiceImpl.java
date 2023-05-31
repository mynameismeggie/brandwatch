package com.brandwatch.store.domain.service.impl;

import com.brandwatch.store.domain.entity.Product;
import com.brandwatch.store.domain.repository.ProductRepository;
import com.brandwatch.store.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must be not null when finding by id");
        }
        return productRepository.findById(id);
    }

    @Override
    public Map<Long, Product> findAllByIdIn(List<Long> ids) {
        if (ids == null) {
            throw new IllegalArgumentException("Ids must be not null when finding by ids");
        }
        return productRepository.findByIdIn(ids)
                .stream()
                .collect(toMap(Product::id, product -> product));
    }

    @Override
    public Map<Long, Product> findAllAsMap() {
        return productRepository.findAll()
                .stream()
                .collect(toMap(Product::id, product -> product));
    }

    @Override
    public Product create(Product product) {
        if (product == null || product.id() != null) {
            throw new IllegalArgumentException("Id must be null when creating product");
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> updateAll(Iterable<Product> products) {
        if (products == null) {
            throw new IllegalArgumentException("Products list cannot be null when updating all");
        }
        return productRepository.saveAll(products);
    }

    @Override
    public Product update(Product product) {
        if (product == null || product.id() == null) {
            throw new IllegalArgumentException("Id must be not null when updating product");
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            productRepository.deleteById(id);
        }
    }
}
