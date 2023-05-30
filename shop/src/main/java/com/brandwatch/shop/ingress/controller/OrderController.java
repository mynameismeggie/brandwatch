package com.brandwatch.shop.ingress.controller;

import com.brandwatch.shop.ingress.facade.OrderFacade;
import com.brandwatch.shop.ingress.request.CreateOrderRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderFacade orderFacade;

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody @NotNull @Valid CreateOrderRequest request) {
        orderFacade.create(request);
        return ResponseEntity.ok("Your order has been submitted");
    }
}
