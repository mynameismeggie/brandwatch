package com.brandwatch.shop.ingress.mapper;

import com.brandwatch.shop.domain.entity.Order;
import com.brandwatch.shop.ingress.request.OrderRequest;
import com.brandwatch.shop.ingress.request.CreateOrderRequest;
import com.brandwatch.shop.ingress.response.OrderResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(CreateOrderRequest request);

    Order toEntity(OrderRequest request);

    List<Order> toEntity(List<OrderRequest> request);

    OrderRequest toOrderRequest(Order order);
    OrderResponse toOrderResponse(Order order);
    List<OrderResponse> toOrderResponse(List<Order> order);
}