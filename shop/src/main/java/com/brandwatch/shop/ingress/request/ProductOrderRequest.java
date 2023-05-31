package com.brandwatch.shop.ingress.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductOrderRequest(
        @NotNull
        Long id,
        @NotNull
        @Positive
        Long quantity
) {
}
