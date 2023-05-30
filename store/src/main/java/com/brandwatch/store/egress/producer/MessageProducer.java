package com.brandwatch.store.egress.producer;

import com.brandwatch.store.ingress.response.OrderResponse;

import java.util.List;

public interface MessageProducer {
    void sendSuccessfulOrders(List<OrderResponse> successfulOrders);
}
