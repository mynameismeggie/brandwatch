package com.brandwatch.store.unit.domain.service;

import com.brandwatch.store.domain.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.brandwatch.store.domain.service.impl.ProductUtility.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceHelperTest {
    @Test
    void isOrderCompletable_Success() {
        // given
        final List<Product> products = getProductList(7L, 5L);
        final Map<Long, Product> productDbMap = getProductMap();

        // when
        final var result = isOrderCompletable(products, productDbMap);

        // then
        assertTrue(result);
    }

    @Test
    void isOrderCompletable_Fail() {
        // given
        final List<Product> products = getProductList(17L, 15L);
        final Map<Long, Product> productDbMap = getProductMap();

        // when
        final var result = isOrderCompletable(products, productDbMap);

        // then
        assertFalse(result);
    }

    @Test
    void addProductQuantities_Success() {
        // given
        final List<Product> products = getProdutsList(17L, 17L);
        final HashMap<Long, Product> productDbMap = getProductMap();

        // when
        addProductQuantities(products, productDbMap);

        // then
        assertEquals(27L, productDbMap.get(1L).quantity());
        assertEquals(27L, productDbMap.get(2L).quantity());
        assertEquals(10L, productDbMap.get(3L).quantity());
    }


    @Test
    void removeProductQualities_Success() {
        // given
        final List<Product> products = getProdutsList(7L, 8L);

        final HashMap<Long, Product> productDbMap = getProductMap();
        // when
        removeProductQuantities(products, productDbMap);
        // then
        assertEquals(3L, productDbMap.get(1L).quantity());
        assertEquals(2L, productDbMap.get(2L).quantity());
        assertEquals(10L, productDbMap.get(3L).quantity());
    }

    private List<Product> getProductList(long quantity, long quantity1) {
        return List.of(
                Product.builder().id(1L).quantity(quantity).build(),
                Product.builder().id(2L).quantity(quantity1).build());
    }

    private HashMap<Long, Product> getProductMap() {
        final var productDbMap = new HashMap<Long, Product>();
        productDbMap.put(1L, Product.builder().id(1L).quantity(10L).name("banana").build());
        productDbMap.put(2L, Product.builder().id(2L).quantity(10L).name("cherry").build());
        productDbMap.put(3L, Product.builder().id(3L).quantity(10L).name("apple").build());
        return productDbMap;
    }

    private List<Product> getProdutsList(long quantity, long quantity1) {
        final List<Product> products = new ArrayList<>();
        products.add(Product.builder()
                .id(1L)
                .quantity(quantity)
                .build());
        products.add(Product.builder()
                .id(2L)
                .quantity(quantity1)
                .build());
        return products;
    }

}
