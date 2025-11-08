package com.umg.lrperezc.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(
        Integer id,
        Integer clientId,
        String status,
        LocalDateTime orderDate,
        String notes,
        List<ItemDTO> items,
        BigDecimal total
) {}
