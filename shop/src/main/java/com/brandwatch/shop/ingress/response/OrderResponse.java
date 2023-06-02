package com.brandwatch.shop.ingress.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public record OrderResponse(
        @NotNull
        Long id,

        @NotEmpty
        List<ProductOrderResponse> products
) implements Serializable {
}
