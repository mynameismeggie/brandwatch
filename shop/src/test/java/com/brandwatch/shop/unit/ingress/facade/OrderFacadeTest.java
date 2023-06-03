package com.brandwatch.shop.unit.ingress.facade;

import com.brandwatch.shop.domain.entity.Order;
import com.brandwatch.shop.domain.entity.ProductOrder;
import com.brandwatch.shop.domain.service.OrderService;
import com.brandwatch.shop.egress.producer.OrderMessageProducer;
import com.brandwatch.shop.ingress.facade.impl.OrderFacadeImpl;
import com.brandwatch.shop.ingress.mapper.OrderMapper;
import com.brandwatch.shop.ingress.request.CreateOrderRequest;
import com.brandwatch.shop.ingress.request.OrderRequest;
import com.brandwatch.shop.ingress.request.ProductOrderRequest;
import com.brandwatch.shop.ingress.response.OrderResponse;
import com.brandwatch.shop.ingress.response.ProductOrderResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.brandwatch.shop.domain.enums.OrderStatus.PENDING;
import static com.brandwatch.shop.domain.enums.OrderStatus.SUCCESSFUL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderFacadeTest {
    @Mock
    private OrderService orderService;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private OrderMessageProducer orderMessageProducer;
    @InjectMocks
    private OrderFacadeImpl test;

    @Test
    void findAllPendingProduct_Success() {
        // given
        final List<ProductOrder> pendingProducts = new ArrayList<>();
        pendingProducts.add(ProductOrder.builder().id(1L).productId(2L).build());

        when(orderService.findDistinctProductsByStatus(PENDING))
                .thenReturn(pendingProducts);

        final List<ProductOrderResponse> response = List.of(
                ProductOrderResponse.builder()
                        .productId(1L)
                        .quantity(2L)
                        .build());

        when(orderMapper.toProductOrderResponse(pendingProducts)).thenReturn(response);
        // when
        final var result = test.findAllPendingProducts();
        // then
        verify(orderService).findDistinctProductsByStatus(PENDING);
        verify(orderMapper).toProductOrderResponse(pendingProducts);
        assertEquals(response, result);
    }

    @Test
    void findAllPendingOrders_Success() {
        // given
        final List<Order> pendingOrders = new ArrayList<>();
        pendingOrders.add(Order.builder()
                .id(1L).products(List.of(ProductOrder
                        .builder()
                        .id(1L)
                        .productId(5L)
                        .quantity(7L)
                        .build()))
                .build());

        when(orderService.findAllByStatus(PENDING)).thenReturn(pendingOrders);

        final var mappedOrder = List.of(OrderResponse
                .builder().id(1L)
                .products(List.of(ProductOrderResponse
                        .builder()
                        .productId(5L)
                        .quantity(7L)
                        .build()))
                .build());

        when(orderMapper.toOrderResponse(pendingOrders)).thenReturn(mappedOrder);
        // when
        final var result = test.findAllPendingOrders();

        // then
        verify(orderService).findAllByStatus(PENDING);
        verify(orderMapper).toOrderResponse(pendingOrders);
        assertEquals(mappedOrder, result);
    }

    @Test
    void create_Success() {
        final var request = CreateOrderRequest.builder()
                .products(List.of(ProductOrderRequest.builder()
                        .productId(7L)
                        .quantity(10L)
                        .build()))
                .build();
        final var entityToSave = Order.builder()
                .id(1L).products(List.of(ProductOrder
                        .builder()
                        .id(1L)
                        .productId(7L)
                        .quantity(10L)
                        .build()))
                .build();

        when(orderMapper.toEntity(request, PENDING)).thenReturn(entityToSave);
        when(orderService.create(entityToSave)).thenReturn(entityToSave);

        final OrderRequest mappedEntity = OrderRequest.builder()
                .products(List.of(ProductOrderRequest.builder()
                        .productId(7L)
                        .quantity(10L)
                        .build()))
                .id(1L)
                .build();
        when(orderMapper.toOrderRequest(entityToSave)).thenReturn(mappedEntity);

        // when
        test.create(request);

        // then
        verify(orderMapper).toEntity(request, PENDING);
        verify(orderService).create(entityToSave);
        verify(orderMapper).toOrderRequest(entityToSave);
    }

    @Test
    void handleSuccessfulOrders_Success() {
        // given
        final var orderRequest = List.of(OrderRequest.builder()
                .products(List.of(ProductOrderRequest.builder()
                        .productId(7L)
                        .quantity(10L)
                        .build()))
                .id(1L)
                .build());
        final List<Order> orderList = List.of(Order.builder()
                .id(1L).products(List.of(ProductOrder
                        .builder()
                        .id(1L)
                        .productId(5L)
                        .quantity(7L)
                        .build()))
                .build());
        when(orderMapper.toEntity(orderRequest)).thenReturn(orderList);
        // when
        test.handleSuccessfulOrders(orderRequest);
        // then
        verify(orderMapper).toEntity(orderRequest);
        verify(orderService).updateStatusByIds(List.of(1L), SUCCESSFUL);
    }
}
