package com.brandwatch.store.ingress.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
//    GET REST endpoint – checks and returns for each product the number that is requested but not in stock
//    POST REST endpoint – loads more products into the Store - (productId, quantity)
}
