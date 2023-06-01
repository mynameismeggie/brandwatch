package com.brandwatch.store.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
public class Product extends BaseEntity {
    @NotBlank
    private String name;

    @PositiveOrZero
    @NotNull
    private Long quantity;
}
