package com.brandwatch.store.unit.ingress.facade;

import com.brandwatch.store.domain.entity.Product;
import com.brandwatch.store.domain.service.ProductService;
import com.brandwatch.store.egress.producer.ProductMessageProducer;
import com.brandwatch.store.ingress.facade.impl.ProductFacadeImpl;
import com.brandwatch.store.ingress.mapper.OrderMapper;
import com.brandwatch.store.ingress.mapper.ProductMapper;
import com.brandwatch.store.ingress.request.OrderRequest;
import com.brandwatch.store.ingress.request.ProductRequest;
import com.brandwatch.store.ingress.response.OrderResponse;
import com.brandwatch.store.ingress.response.ProductOrderResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductFacadeTests {
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private ProductService productService;
    @Mock
    private ProductMessageProducer productMessageProducer;
    @InjectMocks
    private ProductFacadeImpl test;

    @Test
    void getMissingProducts_NoPendingOrders_Success() {
        // given
        final List<ProductOrderResponse> pendingProducts = emptyList();
        when(productMessageProducer
                .getPendingProducts())
                .thenReturn(pendingProducts);

        final var productDbMap = Map.of(1L, mock(Product.class));
        when(productService
                .findAllAsMap())
                .thenReturn(productDbMap);

        // when
        final var result = test.getMissingProducts();

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getMissingProducts_WithPendingProducts_Success() {
        // given
        final List<ProductOrderResponse> pendingProducts = getProductOrderResponses();

        when(productMessageProducer
                .getPendingProducts())
                .thenReturn(pendingProducts);

        final Map<Long, Product> productDbMap = getProductDbMap();

        when(productService.findAllAsMap()).thenReturn(productDbMap);

        //when
        final var result = test.getMissingProducts();

        //then
        final var expected = List.of(
                getProductOrderResponse(1L, 4L),
                getProductOrderResponse(3L, 10L));

        assertEquals(expected, result);
    }


    @Test
    void loadProducts_NoLoadedProducts_Success() {
        //given
        final List<ProductRequest> request = emptyList();
        final List<OrderResponse> pendingProducts = emptyList();
        when(productMessageProducer
                .getPendingOrders())
                .thenReturn(pendingProducts);

        final var productDbMap = Map.of(1L, mock(Product.class));
        when(productService
                .findAllAsMap())
                .thenReturn(productDbMap);

        //when
        test.loadProducts(request);

        //then
        verify(productMessageProducer).getPendingOrders();
        verify(productService).findAllAsMap();
        verify(productService).updateAll(productDbMap.values());
        verify(productMessageProducer, times(0)).sendSuccessfulOrders(any());
    }

    @Test
    void loadProducts_WithLoadedProducts_Success() {
        //given
        final List<ProductRequest> request = getProductRequests();

        final List<OrderResponse> pendingOrders = getOrderResponseList();
        when(productMessageProducer
                .getPendingOrders())
                .thenReturn(pendingOrders);

        final var productDbMap = getProductDbMap();

        when(productService
                .findAllAsMap())
                .thenReturn(productDbMap);

        when(productMapper.toEntityFromRequest(request)).thenReturn(List.of(
                getProductEntity(1L, 17L),
                getProductEntity(2L, 17L)));

        when(productMapper.toEntityFromResponse(List.of(
                getProductOrderResponse(1L, 7L))))
                .thenReturn(List.of(
                        getProductEntity(1L, 7L)));

        //when
        test.loadProducts(request);

        //then
        verify(productMessageProducer).getPendingOrders();
        verify(productService).findAllAsMap();
        verify(productService).updateAll(productDbMap.values());
        verify(productMessageProducer).sendSuccessfulOrders(any());
    }

    @Test
    void handleNewOrder_Success() {
        // given
        final OrderRequest request = getOrderRequest(7L, 5L);
        final var productDbMap = getProductDbMap();

        when(productService
                .findAllByIdIn(List.of(1L, 2L)))
                .thenReturn(productDbMap);
        when(productMapper.toEntityFromRequest(request.products())).thenReturn(getProductList(10, 10));
        when(orderMapper.toOrderResponse(request)).thenReturn(getOrderResponse());

        // when
        test.handleNewOrder(request);

        // then
        verify(productService).findAllByIdIn(List.of(1L, 2L));
        verify(productService).updateAll(productDbMap.values());
        verify(productMessageProducer).sendSuccessfulOrders(any());
    }

    @Test
    void handleNewOrder_Fail() {
        // given
        final OrderRequest request =
                getOrderRequest(18L, 30L);

        final var productDbMap = new HashMap<Long, Product>();
        productDbMap.put(1L, Product.builder().id(1L).quantity(10L).name("banana").build());
        productDbMap.put(2L, Product.builder().id(2L).quantity(10L).name("cherry").build());
        productDbMap.put(3L, Product.builder().id(3L).quantity(10L).name("apple").build());

        when(productService
                .findAllByIdIn(List.of(1L, 2L)))
                .thenReturn(productDbMap);

        when(productMapper.toEntityFromRequest(request.products())).thenReturn(getProductList(18L, 30L));

        // when
        test.handleNewOrder(request);

        // then
        verify(productService).findAllByIdIn(List.of(1L, 2L));
        verify(productService, times(0)).updateAll(productDbMap.values());
        verify(productMessageProducer, times(0)).sendSuccessfulOrders(any());
    }

    private List<ProductOrderResponse> getProductOrderResponses() {
        return List.of(
                getProductOrderResponse(1L, 14L),
                getProductOrderResponse(2L, 10L),
                getProductOrderResponse(3L, 20L));

    }

    private Map<Long, Product> getProductDbMap() {
        final var productMap = new HashMap<Long, Product>();
        productMap.put(1L, Product.builder().id(1L).quantity(10L).name("banana").build());
        productMap.put(2L, Product.builder().id(2L).quantity(10L).name("cherry").build());
        productMap.put(3L, Product.builder().id(3L).quantity(10L).name("apple").build());
        return productMap;
    }

    private List<ProductRequest> getProductRequests() {
        final List<ProductRequest> request = new ArrayList<>();
        request.add(ProductRequest.builder()
                .id(1L)
                .quantity(17L)
                .build());
        request.add(ProductRequest.builder()
                .id(2L)
                .quantity(17L)
                .build());
        return request;
    }

    private List<Product> getProductList(long quantity, long quantity1) {
        return List.of(
                getProductEntity(1L, quantity),
                getProductEntity(2L, quantity1));
    }

    private List<OrderResponse> getOrderResponseList() {
        return List.of(OrderResponse.builder()
                .id(5L)
                .products(List.of(
                        getProductOrderResponse(1L, 7L)))
                .build());
    }

    private Product getProductEntity(long id, long quantity) {
        return Product.builder()
                .id(id)
                .quantity(quantity)
                .build();
    }

    private ProductOrderResponse getProductOrderResponse(long id, long quantity) {
        return ProductOrderResponse.builder()
                .id(id)
                .quantity(quantity)
                .build();
    }

    private OrderRequest getOrderRequest(long quantity, long quantity1) {
        return OrderRequest.builder()
                .id(5L)
                .products(List.of(
                        ProductRequest.builder()
                                .id(1L)
                                .quantity(quantity)
                                .build(),
                        ProductRequest.builder()
                                .id(2L)
                                .quantity(quantity1)
                                .build()))
                .build();
    }

    private OrderResponse getOrderResponse() {
        return OrderResponse.builder()
                .id(5L)
                .products(List.of(
                        getProductOrderResponse(1L, 7L),
                        getProductOrderResponse(2L, 5L)))
                .build();
    }
}
