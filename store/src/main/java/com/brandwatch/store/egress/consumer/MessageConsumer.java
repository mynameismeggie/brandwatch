package com.brandwatch.store.egress.consumer;

import com.brandwatch.store.ingress.response.OrderResponse;
import com.brandwatch.store.ingress.response.ProductOrderResponse;

import java.util.List;

public interface MessageConsumer {
    List<ProductOrderResponse> getPendingProducts();

    List<OrderResponse> getPendingOrders();
}
