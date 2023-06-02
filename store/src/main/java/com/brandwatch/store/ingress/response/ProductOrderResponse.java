package com.brandwatch.store.ingress.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record ProductOrderResponse(
        @NotNull
        @Positive
        Long id,
        @NotNull
        @Positive
        Long quantity
) implements Serializable {
}
