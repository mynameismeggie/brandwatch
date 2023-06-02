package com.brandwatch.shop.ingress.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderRequest(
        @NotNull
        Long id,
        @NotEmpty
        List<ProductOrderRequest> products
) {
}
