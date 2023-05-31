package com.brandwatch.shop.ingress.mapper;

import com.brandwatch.shop.domain.entity.ProductOrder;
import com.brandwatch.shop.ingress.request.ProductOrderRequest;
import com.brandwatch.shop.ingress.response.ProductOrderResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductOrderMapper {
    ProductOrder toEntity(ProductOrderRequest request);

    ProductOrderResponse toProductOrderResponse(ProductOrder productOrders);

    List<ProductOrderResponse> toProductOrderResponse(List<ProductOrder> productOrders);
}
