package com.brandwatch.shop.ingress.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ProductOrderResponse(
        @NotNull
        UUID id,

        @NotNull
        @Positive
        Long requiredQuantity
) {
}
