package com.brandwatch.store.ingress.consumer;

import com.brandwatch.store.ingress.request.OrderRequest;

public interface ProductMessageConsumer {
    void handleNewOrder(OrderRequest request);
}
