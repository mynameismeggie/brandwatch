package com.brandwatch.shop.ingress.consumer;

import com.brandwatch.shop.ingress.request.OrderRequest;
import com.brandwatch.shop.ingress.response.OrderResponse;
import com.brandwatch.shop.ingress.response.ProductOrderResponse;

import java.util.List;

public interface OrderMessageConsumer {
    void handleSuccessfulOrders(List<OrderRequest> orderRequests);

    List<ProductOrderResponse> findAllPendingProducts();

    List<OrderResponse> findAllPendingOrders();
}
