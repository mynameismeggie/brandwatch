package com.brandwatch.store.ingress.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record ProductRequest(
        @NotNull
        @Positive
        Long id,
        @NotNull
        @Positive
        Long quantity
) {
}
