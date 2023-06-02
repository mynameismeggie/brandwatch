package com.brandwatch.shop.unit.domain.service;

import com.brandwatch.shop.domain.entity.Order;
import com.brandwatch.shop.domain.entity.ProductOrder;
import com.brandwatch.shop.domain.repository.OrderRepository;
import com.brandwatch.shop.domain.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.brandwatch.shop.domain.enums.OrderStatus.PENDING;
import static com.brandwatch.shop.domain.enums.OrderStatus.SUCCESSFUL;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderServiceImpl test;

    @Test
    void findAll_Success() {
        // given
        when(orderRepository
                .findAll())
                .thenReturn(emptyList());

        // when
        final var result = test.findAll();

        // then
        verify(orderRepository).findAll();
        assertTrue(result.isEmpty());
    }

    @Test
    void findAllByStatus_Success() {
        //when
        final var result = test.findAllByStatus(PENDING);

        // then
        verify(orderRepository).findAllByStatus(PENDING);
        assertEquals(emptyList(), result);
    }

    @Test
    void findAllByStatus_Throw() {
        // given/when
        final var exception = assertThrows(Exception.class, () -> test.findAllByStatus(null));

        // then
        verify(orderRepository, times(0)).findAllByStatus(null);
        assertEquals("Status must be not null when finding by status", exception.getMessage());
    }

    @Test
    void findById_Success() {
        //given
        final var id = 1L;

        //when
        final var result = test.findById(id);

        // then
        verify(orderRepository).findById(id);
        assertEquals(Optional.empty(), result);
    }

    @Test
    void findById_Throws() {
        //given
        final Long id = null;

        //when
        final var exception = assertThrows(Exception.class, () -> test.findById(id));

        // then
        assertEquals("Id must be not null when finding by id", exception.getMessage());
    }

    @Test
    void create_Success() {
        // given
        final var order = Order.builder()
                .id(null).products(List.of(ProductOrder
                        .builder()
                        .id(1L)
                        .productId(7L)
                        .quantity(10L)
                        .build()))
                .build();
        when(orderRepository.save(order))
                .thenReturn(Order.builder()
                        .id(1L).products(List.of(ProductOrder
                                .builder()
                                .id(1L)
                                .productId(7L)
                                .quantity(10L)
                                .build()))
                        .build());

        // when
        final var result = test.create(order);

        // then
        verify(orderRepository).save(order);
        assertEquals(1L, result.id());
    }

    @Test
    void create_Throw() {
        // given
        final var order = Order.builder()
                .id(1L).products(List.of(ProductOrder
                        .builder()
                        .id(1L)
                        .productId(7L)
                        .quantity(10L)
                        .build()))
                .build();
        // when
        final var result = assertThrows((Exception.class), () -> test.create(order));

        // then
        verify(orderRepository, times(0)).save(order);
        assertEquals("Id must be null when creating order", result.getMessage());
    }

    @Test
    void update_Success() {
        // given
        final var order = Order.builder()
                .id(1L).products(List.of(ProductOrder
                        .builder()
                        .id(1L)
                        .productId(7L)
                        .quantity(10L)
                        .build()))
                .build();
        when(orderRepository.save(order))
                .thenReturn(Order.builder()
                        .id(1L).products(List.of(ProductOrder
                                .builder()
                                .id(1L)
                                .productId(7L)
                                .quantity(17L)
                                .build()))
                        .build());

        // when
        final var result = test.update(order);

        // then
        verify(orderRepository).save(order);
        assertEquals(1L, result.id());
        assertEquals(17L, result.products().get(0).quantity());
    }

    @Test
    void update_Throw() {
        // given
        final Order order = null;

        // when
        final var result = assertThrows((Exception.class), () -> test.update(order));

        // then
        verify(orderRepository, times(0)).save(order);
        assertEquals("Id must be not null when updating order", result.getMessage());
    }

    @Test
    void deleteById_Success() {
        //given
        final var id = 1L;

        //when
        test.deleteById(id);

        // then
        verify(orderRepository).deleteById(id);
    }

    @Test
    void updateStatusByIds_Success(){
        // given
        final var ids = List.of(1L, 2L);
        // when
        test.updateStatusByIds(ids, PENDING);
        // then
        verify(orderRepository).updateStatusByIdIn(PENDING, ids);
    }

    @Test
    void updateStatusByIds_Throw(){
        // given
        final List<Long> ids = null;
        // when
        final var result = assertThrows((Exception.class), () -> test.updateStatusByIds(ids, null));
        // then
        verify(orderRepository, times(0)).updateStatusByIdIn(null, ids);
        assertEquals("Id and status must be not null when updating orders", result.getMessage());
    }

    @Test
    void findDistinctProductsByStatus_Success() {
        // given
        final List<Order> productOrders = List.of(Order.builder()
                .id(1L).products(List.of(ProductOrder
                        .builder()
                        .id(1L)
                        .productId(5L)
                        .quantity(7L)
                        .build()))
                .build());
        when(orderRepository.findAllByStatus(SUCCESSFUL)).thenReturn(productOrders);
        // when
        test.findDistinctProductsByStatus(SUCCESSFUL);
        // then
        verify(orderRepository).findAllByStatus(SUCCESSFUL);
    }

    @Test
    void findDistinctProductsByStatus_Throw() {
        // given/when
        final var result = assertThrows((Exception.class), () -> test.findDistinctProductsByStatus(null));
        // then
        verify(orderRepository, times(0)).findAllByStatus(SUCCESSFUL);
        assertEquals("Status must be not null when finding by status", result.getMessage());
    }

}
