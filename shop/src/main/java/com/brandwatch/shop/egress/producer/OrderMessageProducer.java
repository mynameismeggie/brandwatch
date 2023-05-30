package com.brandwatch.shop.egress.producer;

import com.brandwatch.shop.ingress.request.OrderRequest;

public interface OrderMessageProducer {
    void sendNewOrder(OrderRequest orderRequest);
}
