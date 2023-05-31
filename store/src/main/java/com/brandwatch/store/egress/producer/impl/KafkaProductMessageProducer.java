package com.brandwatch.store.egress.producer.impl;

import com.brandwatch.store.egress.producer.ProductMessageProducer;
import com.brandwatch.store.ingress.response.OrderResponse;
import com.brandwatch.store.ingress.response.ProductOrderResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaProductMessageProducer implements ProductMessageProducer {
    @Override
    public List<ProductOrderResponse> getPendingProducts() {
        return List.of();
    }

    @Override
    public List<OrderResponse> getPendingOrders() {
        return List.of();
    }

    @Override
    public List<OrderResponse> sendSuccessfulOrders(List<OrderResponse> successfulOrders) {
        return successfulOrders;
    }
}
