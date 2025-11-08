package com.umg.lrperezc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItem {

    @Column(name = "item_title", nullable = false, length = 150)
    private String title;

    @Column(name = "item_price", precision = 12, scale = 2, nullable = false)
    private BigDecimal price;
}
