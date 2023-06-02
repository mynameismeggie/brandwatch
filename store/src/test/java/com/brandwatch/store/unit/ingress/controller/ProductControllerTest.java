package com.brandwatch.store.unit.ingress.controller;

import com.brandwatch.store.ingress.controller.ProductController;
import com.brandwatch.store.ingress.facade.ProductFacade;
import com.brandwatch.store.ingress.request.ProductRequest;
import com.brandwatch.store.ingress.response.ProductOrderResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
final class ProductControllerTest {
    @Mock
    private ProductFacade facade;

    @InjectMocks
    private ProductController test;

    @Test
    public void getMissingProducts_Success() {
        //given
        final var response = List.of(mock(ProductOrderResponse.class));
        when(facade.getMissingProducts())
                .thenReturn(response);
        //when
        final var result = test.getMissingProducts();
        //then
        assertEquals(OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void loadProducts_Success(){
        //given
        final var request = List.of(mock(ProductRequest.class));
        //when
        final var result = test.loadProducts(request);
        //then
        assertEquals(OK, result.getStatusCode());
        assertNull(result.getBody());
    }
}
