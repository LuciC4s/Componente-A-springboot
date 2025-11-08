package com.umg.lrperezc.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record CreateOrderDTO(
        @NotNull Integer clientId,
        @Pattern(regexp = "(?i)pagado|pendiente", message = "Estado inválido, use 'pagado' o 'pendiente'") String status,
        String notes,
        @NotEmpty List<@Valid ItemDTO> items
) {}
