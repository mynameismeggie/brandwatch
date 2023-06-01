package com.brandwatch.store.ingress.mapper;

import com.brandwatch.store.ingress.request.OrderRequest;
import com.brandwatch.store.ingress.request.ProductRequest;
import com.brandwatch.store.ingress.response.OrderResponse;
import com.brandwatch.store.ingress.response.ProductOrderResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toOrderResponse(OrderRequest request);

    List<ProductOrderResponse> toOrderResponse(List<ProductRequest> response);

    ProductOrderResponse toOrderResponse(ProductRequest response);
}
