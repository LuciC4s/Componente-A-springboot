package com.umg.lrperezc.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemDTO(
        @NotBlank String title,
        @NotNull @DecimalMin(value = "0.00", inclusive = true) BigDecimal price
) {}
