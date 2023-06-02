package com.brandwatch.store.ingress.consumer.impl;

import com.brandwatch.store.ingress.consumer.ProductMessageConsumer;
import com.brandwatch.store.ingress.facade.ProductFacade;
import com.brandwatch.store.ingress.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.brandwatch.store.configuration.RabbitMqConfig.NEW_ORDER_QUEUE;

@Component
@RequiredArgsConstructor
public class RabbitMqProductMessageListener implements ProductMessageConsumer {
    private final ProductFacade productFacade;

    @Override
    @RabbitListener(queues = NEW_ORDER_QUEUE)
    public void handleNewOrder(OrderRequest request) {
        productFacade.handleNewOrder(request);
    }
}
