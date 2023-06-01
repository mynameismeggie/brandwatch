package com.brandwatch.store.egress.producer;

import com.brandwatch.store.ingress.response.OrderResponse;
import com.brandwatch.store.ingress.response.ProductOrderResponse;

import java.util.List;
/**
 * Used for producing product and order messages
 */
public interface ProductMessageProducer {
    /**
     * Creates an event to retrieve the pending products from required total quantity per order topic
     * @return list of pending products
     */
    List<ProductOrderResponse> getPendingProducts();

    /**
     * Creates an event to retrieve the pending orders from pending orders topic
     * @return list of pending orders
     */
    List<OrderResponse> getPendingOrders();

    /**
     *  Asynchronously creates an event that an order is successfully completed
     * @param successfulOrders accepts list of successful orders
     * @return list of OrderResponse
     */
    List<OrderResponse> sendSuccessfulOrders(List<OrderResponse> successfulOrders);
}
