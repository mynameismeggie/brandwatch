package com.brandwatch.shop.ingress.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record OrderResponse(
        @NotNull
        UUID id,

        @NotEmpty
        List<ProductOrderResponse> products
) {
}
