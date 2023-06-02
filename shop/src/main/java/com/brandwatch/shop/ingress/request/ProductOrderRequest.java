package com.brandwatch.shop.ingress.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record ProductOrderRequest(
        @NotNull
        Long productId,
        @NotNull
        @Positive
        Long quantity
) {
}
