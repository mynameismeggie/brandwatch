package com.brandwatch.shop.ingress.consumer;

import com.brandwatch.shop.ingress.request.OrderRequest;
import com.brandwatch.shop.ingress.response.OrderResponse;
import com.brandwatch.shop.ingress.response.ProductOrderResponse;

import java.util.List;

/**
 * Used for handling incoming order messages
 */
public interface OrderMessageConsumer {
    /**
     * Reads from a successful orders topic and updates the status to COMPLETED in database
     *
     * @param orderRequests accepts list of OrderRequest
     */
    void handleSuccessfulOrders(List<OrderRequest> orderRequests);

    /**
     * Reads from required total quantity per order topic and returns required quantity per product
     *
     * @return accepts list of PendingOrderResponse
     */
    List<ProductOrderResponse> findAllPendingProducts();

    /**
     * Reads from pending orders topic and returns all pending orders
     *
     * @return accepts list of OrderResponse
     */
    List<OrderResponse> findAllPendingOrders();
}
