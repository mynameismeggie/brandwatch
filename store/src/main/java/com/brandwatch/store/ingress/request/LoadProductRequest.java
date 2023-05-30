package com.brandwatch.store.ingress.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.util.UUID;

@Builder
public record LoadProductRequest(
        @NotNull
        UUID id,
        @NotNull
        @Positive
        Long quantity
) {
}
