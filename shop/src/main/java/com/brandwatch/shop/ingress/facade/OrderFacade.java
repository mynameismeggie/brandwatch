package com.brandwatch.shop.ingress.facade;

import com.brandwatch.shop.ingress.request.CreateOrderRequest;
import com.brandwatch.shop.ingress.request.OrderRequest;
import com.brandwatch.shop.ingress.response.OrderResponse;
import com.brandwatch.shop.ingress.response.ProductOrderResponse;

import java.util.List;

/**
 * Used for serving the endpoint logic of the Order Controller
 */
public interface OrderFacade {
    /**
     * Finds all pending products
     *
     * @return list of all pending product order response
     */
    List<ProductOrderResponse> findAllPendingProducts();

    /**
     * Finds all pending orders
     *
     * @return list of all order response
     */
    List<OrderResponse> findAllPendingOrders();

    /**
     * Creates new entity
     * @param request accepts request which contains list of ProductOrderRequest
     */
    void create(CreateOrderRequest request);

    /**
     * Handles successful orders
     * @param orderRequests accepts list of OrderRequest
     */
    void handleSuccessfulOrders(List<OrderRequest> orderRequests);
}
