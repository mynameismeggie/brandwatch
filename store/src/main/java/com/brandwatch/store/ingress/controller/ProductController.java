package com.brandwatch.store.ingress.controller;

import com.brandwatch.store.ingress.facade.ProductFacade;
import com.brandwatch.store.ingress.request.LoadProductRequest;
import com.brandwatch.store.ingress.response.ProductOrderResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductFacade productFacade;

    @GetMapping()
    public ResponseEntity<List<ProductOrderResponse>> getMissingProducts() {
        final var products = productFacade.getMissingProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping()
    public ResponseEntity<Void> loadProducts(@RequestBody @NotNull @Valid final List<LoadProductRequest> loadProductRequests) {
        productFacade.loadProducts(loadProductRequests);
        return ResponseEntity.ok().build();
    }
}
