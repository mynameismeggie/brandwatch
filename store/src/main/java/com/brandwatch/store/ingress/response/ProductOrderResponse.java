package com.brandwatch.store.ingress.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record ProductOrderResponse(
        @NotNull
        @Positive
        Long id,
        @NotNull
        @Positive
        Long quantity
) {
}
