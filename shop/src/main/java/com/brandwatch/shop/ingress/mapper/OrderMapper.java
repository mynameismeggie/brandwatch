package com.brandwatch.shop.ingress.mapper;

import com.brandwatch.shop.domain.entity.Order;
import com.brandwatch.shop.domain.entity.ProductOrder;
import com.brandwatch.shop.domain.enums.OrderStatus;
import com.brandwatch.shop.ingress.request.CreateOrderRequest;
import com.brandwatch.shop.ingress.request.OrderRequest;
import com.brandwatch.shop.ingress.request.ProductOrderRequest;
import com.brandwatch.shop.ingress.response.OrderResponse;
import com.brandwatch.shop.ingress.response.ProductOrderResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(CreateOrderRequest request);

    Order toEntity(CreateOrderRequest request, OrderStatus status);

    Order toEntity(OrderResponse response);

    Order toEntity(OrderRequest request);

    List<Order> toEntity(List<OrderRequest> request);

    default OrderRequest toOrderRequest(Order order) {
        return OrderRequest.builder()
                .id(order.id())
                .products(toProductOrderRequest(order.products()))
                .build();
    }

    default OrderResponse toOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.id())
                .products(toProductOrderResponse(order.products()))
                .build();
    }

    List<OrderResponse> toOrderResponse(List<Order> order);

    ProductOrder toEntity(ProductOrderRequest request);

    default ProductOrderResponse toProductOrderResponse(ProductOrder productOrders) {
        return ProductOrderResponse.builder()
                .productId(productOrders.productId())
                .quantity(productOrders.quantity())
                .build();
    }

    List<ProductOrderResponse> toProductOrderResponse(List<ProductOrder> productOrders);

    default ProductOrderRequest toProductOrderRequest(ProductOrder productOrder) {
        return ProductOrderRequest.builder()
                .productId(productOrder.productId())
                .quantity(productOrder.quantity())
                .build();
    }

    List<ProductOrderRequest> toProductOrderRequest(List<ProductOrder> productOrder);
}
