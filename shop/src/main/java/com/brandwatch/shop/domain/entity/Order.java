package com.brandwatch.shop.domain.entity;

import com.brandwatch.shop.domain.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.EAGER;

@Getter
@Entity
@Table(name = "order_table")
@Accessors(fluent = true, makeFinal = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Order extends BaseEntity {
    @OneToMany(cascade = ALL, fetch = EAGER)
    private List<ProductOrder> products;

    @NotNull
    @Enumerated(STRING)
    private OrderStatus status;
}
