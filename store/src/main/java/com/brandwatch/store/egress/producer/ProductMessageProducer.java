package com.brandwatch.store.egress.producer;

import com.brandwatch.store.ingress.response.OrderResponse;
import com.brandwatch.store.ingress.response.ProductOrderResponse;

import java.util.List;

public interface ProductMessageProducer {
    List<ProductOrderResponse> getPendingProducts();

    List<OrderResponse> getPendingOrders();

    void sendSuccessfulOrders(List<OrderResponse> successfulOrders);
}
