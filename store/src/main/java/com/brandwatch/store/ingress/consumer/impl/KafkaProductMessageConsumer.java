package com.brandwatch.store.ingress.consumer.impl;

import com.brandwatch.store.ingress.consumer.ProductMessageConsumer;
import com.brandwatch.store.ingress.facade.ProductFacade;
import com.brandwatch.store.ingress.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProductMessageConsumer implements ProductMessageConsumer {
    private final ProductFacade productFacade;

    @Override
    public void handleNewOrder(OrderRequest request) {
        productFacade.handleNewOrder(request);
    }
}
