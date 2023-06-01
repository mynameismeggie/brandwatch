package com.brandwatch.store.ingress.controller;

import com.brandwatch.store.ingress.facade.ProductFacade;
import com.brandwatch.store.ingress.request.ProductRequest;
import com.brandwatch.store.ingress.response.ProductOrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Tag(name = "Product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductFacade productFacade;

    @GetMapping(path = "missing-products")
    @Operation(summary = "Get missing products by pending orders")
    public ResponseEntity<List<ProductOrderResponse>> getMissingProducts() {
        final var products = productFacade.getMissingProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping(path = "load")
    @Operation(summary = "Load requested product quantities")
    public ResponseEntity<Void> loadProducts(@RequestBody @NotNull @Valid final List<ProductRequest> productRequests) {
        productFacade.loadProducts(productRequests);
        return ResponseEntity.ok().build();
    }
}
