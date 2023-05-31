package com.brandwatch.store.ingress.facade;

import com.brandwatch.store.ingress.request.OrderRequest;
import com.brandwatch.store.ingress.request.ProductRequest;
import com.brandwatch.store.ingress.response.ProductOrderResponse;

import java.util.List;

public interface ProductFacade {
    List<ProductOrderResponse> getMissingProducts();

    void loadProducts(List<ProductRequest> loadProducts);

    void handleNewOrder(OrderRequest request);
}
