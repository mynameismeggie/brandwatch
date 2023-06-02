package com.brandwatch.shop.ingress.consumer.impl;

import com.brandwatch.shop.ingress.consumer.OrderMessageConsumer;
import com.brandwatch.shop.ingress.facade.OrderFacade;
import com.brandwatch.shop.ingress.request.OrderRequest;
import com.brandwatch.shop.ingress.response.OrderResponse;
import com.brandwatch.shop.ingress.response.ProductOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.brandwatch.shop.configuration.RabbitMqConfig.*;

@Component
@RequiredArgsConstructor
public class RabbitMqOrderMessageListener implements OrderMessageConsumer {
    private final OrderFacade orderFacade;

    @Override
    @RabbitListener(queues = SUCCESSFUL_ORDERS_QUEUE)
    public void handleSuccessfulOrders(List<OrderRequest> orderRequests) {
        orderFacade.handleSuccessfulOrders(orderRequests);
    }

    @Override
    @RabbitListener(queues = PENDING_PRODUCTS_QUEUE)
    public List<ProductOrderResponse> findAllPendingProducts() {
        return orderFacade.findAllPendingProducts();
    }

    @Override
    @RabbitListener(queues = PENDING_ORDERS_QUEUE)
    public List<OrderResponse> findAllPendingOrders() {
        return orderFacade.findAllPendingOrders();
    }
}
