package com.brandwatch.shop.domain.service.impl;

import com.brandwatch.shop.domain.repository.ProductOrderRepository;
import com.brandwatch.shop.domain.service.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductOrderServiceImpl implements ProductOrderService {
    private final ProductOrderRepository repository;
}
