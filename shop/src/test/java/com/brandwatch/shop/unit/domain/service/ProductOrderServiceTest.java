package com.brandwatch.shop.unit.domain.service;

import com.brandwatch.shop.domain.entity.ProductOrder;
import com.brandwatch.shop.domain.repository.ProductOrderRepository;
import com.brandwatch.shop.domain.service.impl.ProductOrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductOrderServiceTest {
    @Mock
    private ProductOrderRepository repository;
    @InjectMocks
    private ProductOrderServiceImpl test;

    @Test
    void findById_Success() {
        //given
        final var id = 1L;

        //when
        final var result = test.findById(id);

        // then
        verify(repository).findById(id);
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
        final var product = ProductOrder.builder().id(null).quantity(7L).build();
        when(repository.save(product))
                .thenReturn(ProductOrder.builder().id(1L).quantity(7L).build());

        // when
        final var result = test.create(product);

        // then
        verify(repository).save(product);
        assertEquals(1L, result.id());
    }

    @Test
    void create_Throw() {
        // given
        final var product = ProductOrder.builder().id(1L).quantity(7L).build();

        // when
        final var result = assertThrows((Exception.class), () -> test.create(product));

        // then
        verify(repository, times(0)).save(product);
        assertEquals("Id must be null when creating product order", result.getMessage());
    }

    @Test
    void update_Success() {
        // given
        final var product = ProductOrder.builder().id(1L).quantity(7L).build();
        when(repository.save(product))
                .thenReturn(ProductOrder.builder().id(1L).quantity(17L).build());

        // when
        final var result = test.update(product);

        // then
        verify(repository).save(product);
        assertEquals(1L, result.id());
        assertEquals(17L, result.quantity());
    }

    @Test
    void update_Throw() {
        // given
        final ProductOrder product = null;

        // when
        final var result = assertThrows((Exception.class), () -> test.update(product));

        // then
        verify(repository, times(0)).save(product);
        assertEquals("Id must be not null when updating productOrder", result.getMessage());
    }

    @Test
    void deleteById_Success() {
        //given
        final var id = 1L;

        //when
        test.deleteById(id);

        // then
        verify(repository).deleteById(id);
    }
}
