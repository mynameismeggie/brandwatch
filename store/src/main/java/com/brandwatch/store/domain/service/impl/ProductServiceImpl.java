package com.brandwatch.store.domain.service.impl;

import com.brandwatch.store.domain.entity.Product;
import com.brandwatch.store.domain.repository.ProductRepository;
import com.brandwatch.store.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id);
    }

    @Override
    public Product create(Product product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("Id must be null when creating product");
        }
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException("Id must be not null when updating product");
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }
}
