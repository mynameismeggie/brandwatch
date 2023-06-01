package com.brandwatch.store.ingress.facade;

import com.brandwatch.store.ingress.request.OrderRequest;
import com.brandwatch.store.ingress.request.ProductRequest;
import com.brandwatch.store.ingress.response.ProductOrderResponse;

import java.util.List;

/**
 * Used for serving the endpoint logic of the ProductController
 */
public interface ProductFacade {
    /**
     * Gets all missing products
     * @return list of all missing products
     */
    List<ProductOrderResponse> getMissingProducts();

    /**
     * Loads missing products
     * @param loadProducts accepts list of ProductRequest
     */
    void loadProducts(List<ProductRequest> loadProducts);

    /**
     * Handle creation of new orders and checks if the new order is completable
     * @param request accepts OrderRequest
     */
    void handleNewOrder(OrderRequest request);
}
