package com.brandwatch.shop.ingress.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Builder
public record CreateOrderRequest(
        @NotEmpty
        List<ProductOrderRequest> products
) {
}
