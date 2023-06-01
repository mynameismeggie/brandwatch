package com.brandwatch.store.unit.domain.service;

import com.brandwatch.store.domain.entity.Product;
import com.brandwatch.store.domain.repository.ProductRepository;
import com.brandwatch.store.domain.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl test;

    @Test
    void findAll_Success() {
        // given
        when(productRepository
                .findAll())
                .thenReturn(emptyList());

        // when
        final var result = test.findAll();

        // then
        verify(productRepository).findAll();
        assertTrue(result.isEmpty());
    }

    @Test
    void findById_Success() {
        //given
        final var id = 1L;

        //when
        final var result = test.findById(id);

        // then
        verify(productRepository).findById(id);
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
    void findAllByIdIn_Success() {
        // given
        final var ids = List.of(1L, 2L);
        when(productRepository.findByIdIn(ids))
                .thenReturn(List.of(
                        Product.builder().id(1L).quantity(7L).build(),
                        Product.builder().id(2L).quantity(5L).build()));

        // when
        final var result = test.findAllByIdIn(ids);

        // then
        verify(productRepository).findByIdIn(ids);
        assertEquals(7L, result.get(1L).quantity());
        assertEquals(5L, result.get(2L).quantity());
    }

    @Test
    void findAllByIdIn_Throws() {
        // given
        final List<Long> ids = null;

        // when
        final var result = assertThrows((Exception.class), () -> test.findAllByIdIn(ids));

        // then
        assertEquals("Ids must be not null when finding by ids", result.getMessage());
    }

    @Test
    void findAllAsMap_Success() {
        // given
        when(productRepository
                .findAll())
                .thenReturn(List.of(
                        Product.builder().id(1L).quantity(7L).build(),
                        Product.builder().id(2L).quantity(5L).build()));

        // when
        final var result = test.findAllAsMap();

        // then
        verify(productRepository).findAll();
        assertEquals(7L, result.get(1L).quantity());
        assertEquals(5L, result.get(2L).quantity());
    }

    @Test
    void create_Success() {
        // given
        final var product = Product.builder().id(null).quantity(7L).build();
        when(productRepository.save(product))
                .thenReturn(Product.builder().id(1L).quantity(7L).build());

        // when
        final var result = test.create(product);

        // then
        verify(productRepository).save(product);
        assertEquals(1L, result.id());
    }

    @Test
    void create_Throw() {
        // given
        final var product = Product.builder().id(1L).quantity(7L).build();

        // when
        final var result = assertThrows((Exception.class), () -> test.create(product));

        // then
        verify(productRepository, times(0)).save(product);
        assertEquals("Id must be null when creating product", result.getMessage());
    }

    @Test
    void create_Throw_ProductNull() {
        // given
        final Product product = null;

        // when
        final var result = assertThrows((Exception.class), () -> test.create(product));

        // then
        verify(productRepository, times(0)).save(product);
        assertEquals("Id must be null when creating product", result.getMessage());
    }

    @Test
    void updateAll_Success() {
        // given
        final var products = List.of(
                Product.builder().id(1L).quantity(7L).build(),
                Product.builder().id(2L).quantity(17L).build());
        when(productRepository.saveAll(products))
                .thenReturn(List.of(
                        Product.builder().id(1L).quantity(7L).build(),
                        Product.builder().id(2L).quantity(17L).build()));

        // when
        final var result = test.updateAll(products);

        // then
        verify(productRepository).saveAll(products);
        assertEquals(1L, result.get(0).id());
        assertEquals(17L, result.get(1).quantity());
    }

    @Test
    void updateAll_Throw() {
        // given
        final List<Product> products = null;

        // when
        final var result = assertThrows((Exception.class), () -> test.updateAll(products));

        // then
        verify(productRepository, times(0)).saveAll(products);
        assertEquals("Products list cannot be null when updating all", result.getMessage());
    }

    @Test
    void update_Success() {
        // given
        final var product = Product.builder().id(1L).quantity(7L).build();
        when(productRepository.save(product))
                .thenReturn(Product.builder().id(1L).quantity(17L).build());

        // when
        final var result = test.update(product);

        // then
        verify(productRepository).save(product);
        assertEquals(1L, result.id());
        assertEquals(17L, result.quantity());
    }

    @Test
    void update_Throw() {
        // given
        final Product product = null;

        // when
        final var result = assertThrows((Exception.class), () -> test.update(product));

        // then
        verify(productRepository, times(0)).save(product);
        assertEquals("Id must be not null when updating product", result.getMessage());
    }

    @Test
    void deleteById_Success() {
        //given
        final var id = 1L;

        //when
        test.deleteById(id);

        // then
        verify(productRepository).deleteById(id);
    }
}
