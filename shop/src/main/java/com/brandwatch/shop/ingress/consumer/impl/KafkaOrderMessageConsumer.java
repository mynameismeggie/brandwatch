package com.brandwatch.shop.ingress.consumer.impl;

import com.brandwatch.shop.ingress.consumer.OrderMessageConsumer;
import com.brandwatch.shop.ingress.facade.OrderFacade;
import com.brandwatch.shop.ingress.request.OrderRequest;
import com.brandwatch.shop.ingress.response.OrderResponse;
import com.brandwatch.shop.ingress.response.ProductOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaOrderMessageConsumer implements OrderMessageConsumer {
    private final OrderFacade orderFacade;

    @Override
    public void handleSuccessfulOrders(List<OrderRequest> orderRequests) {
        orderFacade.handleSuccessfulOrders(orderRequests);
    }

    @Override
    public List<ProductOrderResponse> findAllPendingProducts() {
        return orderFacade.findAllPendingProducts();
    }

    @Override
    public List<OrderResponse> findAllPendingOrders() {
        return orderFacade.findAllPendingOrders();
    }
}
