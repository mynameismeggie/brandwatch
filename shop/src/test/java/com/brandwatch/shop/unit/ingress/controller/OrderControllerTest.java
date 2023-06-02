package com.brandwatch.shop.unit.ingress.controller;

import com.brandwatch.shop.ingress.controller.OrderController;
import com.brandwatch.shop.ingress.facade.OrderFacade;
import com.brandwatch.shop.ingress.request.CreateOrderRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    @Mock
    private OrderFacade facade;
    @InjectMocks
    private OrderController test;

    @Test
    void create_Success() {
        // given
        final var request = mock(CreateOrderRequest.class);
        // when
        final var result = test.create(request);
        //then
        verify(facade).create(request);
        assertEquals(OK, result.getStatusCode());
        assertEquals("Your order has been submitted", result.getBody());
    }
}
