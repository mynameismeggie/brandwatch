package com.brandwatch.store.egress.producer.impl;

import com.brandwatch.store.egress.producer.MessageProducer;
import com.brandwatch.store.ingress.response.OrderResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaMessageProducer implements MessageProducer {
    @Override
    public void sendSuccessfulOrders(List<OrderResponse> successfulOrders) {
    }
}
