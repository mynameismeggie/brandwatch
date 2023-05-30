package com.brandwatch.shop.ingress.request;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CreateOrderRequest(
        @NotEmpty
        List<ProductOrderRequest> productOrderRequests
) {
}
