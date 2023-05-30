package com.brandwatch.shop.domain.entity;

import com.brandwatch.shop.domain.enums.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Entity
@Table(name = "order")
@Accessors(fluent = true, makeFinal = true)
@SuperBuilder(toBuilder = true)
public class Order extends BaseEntity {
    @OneToMany
    private List<ProductOrder> products;

    @Enumerated
    private OrderStatus status;
}
