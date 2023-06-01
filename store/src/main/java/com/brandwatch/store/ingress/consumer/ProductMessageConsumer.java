package com.brandwatch.store.ingress.consumer;

import com.brandwatch.store.ingress.request.OrderRequest;

/**
 * Used for handling incoming order messages
 */
public interface ProductMessageConsumer {

    /**
     * Reads from new orders topic
     * @param request accepts OrderRequest
     */
    void handleNewOrder(OrderRequest request);
}
