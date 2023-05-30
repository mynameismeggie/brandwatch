package com.brandwatch.store.egress.consumer.impl;

import com.brandwatch.store.egress.consumer.MessageConsumer;
import com.brandwatch.store.ingress.response.OrderResponse;
import com.brandwatch.store.ingress.response.ProductOrderResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaMessageConsumer implements MessageConsumer {
    @Override
    public List<ProductOrderResponse> getPendingProducts() {
        return null;
    }

    @Override
    public List<OrderResponse> getPendingOrders() {
        return null;
    }
}
