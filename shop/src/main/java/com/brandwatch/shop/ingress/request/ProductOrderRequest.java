package com.brandwatch.shop.ingress.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ProductOrderRequest(
        @NotNull
        UUID productId,
        @NotNull
        @Positive
        Long quantity
) {
}
