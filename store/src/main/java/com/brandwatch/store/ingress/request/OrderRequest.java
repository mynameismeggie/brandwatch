package com.brandwatch.store.ingress.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record OrderRequest(
        @NotNull
        @Positive
        Long id,
        List<ProductRequest> products
) {
}
