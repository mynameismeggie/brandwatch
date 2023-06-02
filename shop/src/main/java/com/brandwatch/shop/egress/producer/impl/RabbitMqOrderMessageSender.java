package com.brandwatch.shop.egress.producer.impl;

import com.brandwatch.shop.egress.producer.OrderMessageProducer;
import com.brandwatch.shop.ingress.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static com.brandwatch.shop.configuration.RabbitMqConfig.NEW_ORDER_QUEUE;

@Component
@RequiredArgsConstructor
public class RabbitMqOrderMessageSender implements OrderMessageProducer {
    private final RabbitTemplate template;

    @Override
    public void sendNewOrder(OrderRequest orderRequest) {
        template.convertAndSend(NEW_ORDER_QUEUE, orderRequest);
    }
}
