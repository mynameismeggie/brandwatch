package com.brandwatch.shop.ingress.request;

import java.util.List;

public record OrderRequest(
        Long id,
        List<ProductOrderRequest> products
) {
}
