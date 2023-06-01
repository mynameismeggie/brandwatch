package com.brandwatch.store.ingress.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderResponse(
        @NotNull
        @Positive
        Long id,
        @NotEmpty
        List<ProductOrderResponse> products
) {
}
