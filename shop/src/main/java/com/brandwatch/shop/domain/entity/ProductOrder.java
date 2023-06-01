package com.brandwatch.shop.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@Table(name = "product")
@Accessors(fluent = true, makeFinal = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ProductOrder extends BaseEntity {
    @NotNull
    private Long productId;

    @PositiveOrZero
    @NotNull
    private Long quantity;
}
