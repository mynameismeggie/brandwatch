package com.brandwatch.shop.ingress.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record ProductOrderRequest(
        @NotNull
        @JsonProperty("id")
        Long productId,
        @NotNull
        @Positive
        Long quantity
) {
}
