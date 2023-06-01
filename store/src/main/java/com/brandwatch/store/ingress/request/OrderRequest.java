package com.brandwatch.store.ingress.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderRequest(
        @NotNull
        @Positive
        Long id,
        List<ProductRequest> products
) {
}
