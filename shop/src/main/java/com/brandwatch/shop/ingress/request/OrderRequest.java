package com.brandwatch.shop.ingress.request;

import java.util.List;
import java.util.UUID;

public record OrderRequest(
        UUID id,
        List<ProductOrderRequest> productOrders
) {
}
