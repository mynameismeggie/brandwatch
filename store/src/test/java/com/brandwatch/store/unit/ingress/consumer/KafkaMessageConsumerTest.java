package com.brandwatch.store.unit.ingress.consumer;

import com.brandwatch.store.ingress.consumer.impl.KafkaProductMessageConsumer;
import com.brandwatch.store.ingress.facade.ProductFacade;
import com.brandwatch.store.ingress.request.OrderRequest;
import com.brandwatch.store.ingress.request.ProductRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class KafkaMessageConsumerTest {
    @Mock
    private ProductFacade facade;
    @InjectMocks
    private KafkaProductMessageConsumer test;

    @Test
    void handleNewOrder_Success() {
        // given
        final var request = getOrderRequest();

        // when
        test.handleNewOrder(request);
        // then
        verify(facade).handleNewOrder(request);

    }

    private OrderRequest getOrderRequest() {
        return OrderRequest.builder()
                .id(5L)
                .products(List.of(
                        ProductRequest.builder()
                                .id(1L)
                                .quantity(7L)
                                .build(),
                        ProductRequest.builder()
                                .id(2L)
                                .quantity(5L)
                                .build()))
                .build();
    }
}
