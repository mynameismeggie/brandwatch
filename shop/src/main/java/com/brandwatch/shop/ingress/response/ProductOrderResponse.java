package com.brandwatch.shop.ingress.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record ProductOrderResponse(
        @NotNull
        Long productId,

        @NotNull
        @Positive
        Long quantity
) {
}
