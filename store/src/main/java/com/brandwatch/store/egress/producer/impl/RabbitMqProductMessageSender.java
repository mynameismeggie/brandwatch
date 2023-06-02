package com.brandwatch.store.egress.producer.impl;

import com.brandwatch.store.egress.producer.ProductMessageProducer;
import com.brandwatch.store.ingress.response.OrderResponse;
import com.brandwatch.store.ingress.response.ProductOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.brandwatch.store.configuration.RabbitMqConfig.*;

@Component
@RequiredArgsConstructor
public class RabbitMqProductMessageSender implements ProductMessageProducer {
    private final RabbitTemplate template;

    @Override
    public void sendSuccessfulOrders(List<OrderResponse> successfulOrders) {
        template.convertAndSend(SUCCESSFUL_ORDERS_QUEUE, successfulOrders);
    }

    @Override
    public List<ProductOrderResponse> getPendingProducts() {
        return template.convertSendAndReceiveAsType(PENDING_PRODUCTS_QUEUE, "", new ParameterizedTypeReference<>() {
        });
    }

    @Override
    public List<OrderResponse> getPendingOrders() {
        return template.convertSendAndReceiveAsType(PENDING_ORDERS_QUEUE, "", new ParameterizedTypeReference<>() {
        });
    }
}
