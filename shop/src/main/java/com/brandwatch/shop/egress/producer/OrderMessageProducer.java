package com.brandwatch.shop.egress.producer;

import com.brandwatch.shop.ingress.request.OrderRequest;

/**
 * Used for producing order messages
 */
public interface OrderMessageProducer {
    /**
     * Asynchronously creates an event that a new order is created
     * @param orderRequest accepts OrderRequest
     */
    void sendNewOrder(OrderRequest orderRequest);
}
