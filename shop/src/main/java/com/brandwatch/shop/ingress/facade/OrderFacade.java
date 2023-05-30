package com.brandwatch.shop.ingress.facade;

import com.brandwatch.shop.ingress.request.CreateOrderRequest;
import com.brandwatch.shop.ingress.request.OrderRequest;
import com.brandwatch.shop.ingress.response.OrderResponse;
import com.brandwatch.shop.ingress.response.ProductOrderResponse;

import java.util.List;

public interface OrderFacade {
    List<ProductOrderResponse> findAllPendingProducts();

    List<OrderResponse> findAllPendingOrders();

    void create(CreateOrderRequest request);

    void handleSuccessfulOrders(List<OrderRequest> orderRequests);
}
