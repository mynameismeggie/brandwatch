package com.brandwatch.shop.egress.producer.impl;

import com.brandwatch.shop.egress.producer.OrderMessageProducer;
import com.brandwatch.shop.ingress.request.OrderRequest;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderMessageProducer implements OrderMessageProducer {
    @Override
    public void sendNewOrder(OrderRequest orderRequest) {

    }
}
