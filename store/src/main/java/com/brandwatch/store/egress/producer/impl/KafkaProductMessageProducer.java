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
        return null;
    }

    @Override
    public List<OrderResponse> getPendingOrders() {
        return null;
    }

    @Override
    public void sendSuccessfulOrders(List<OrderResponse> successfulOrders) {
    }
}
