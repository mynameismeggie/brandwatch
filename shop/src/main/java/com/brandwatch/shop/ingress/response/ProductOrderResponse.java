package com.brandwatch.shop.ingress.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record ProductOrderResponse(
        @NotNull
        @JsonProperty("id")
        Long productId,

        @NotNull
        @Positive
        Long quantity
) implements Serializable {
}
