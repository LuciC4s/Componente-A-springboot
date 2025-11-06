package com.umg.lrperezc.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateClientDTO(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @Email @NotBlank String email
) {}
