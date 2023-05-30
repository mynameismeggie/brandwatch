package com.brandwatch.store.ingress.facade;

import com.brandwatch.store.ingress.request.LoadProductRequest;
import com.brandwatch.store.ingress.response.ProductOrderResponse;

import java.util.List;

public interface ProductFacade {
    List<ProductOrderResponse> getMissingProducts();

    void loadProducts(List<LoadProductRequest> loadProducts);
}
