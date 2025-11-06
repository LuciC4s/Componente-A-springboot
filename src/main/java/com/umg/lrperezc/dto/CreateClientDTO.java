package com.umg.lrperezc.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateClientDTO(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String username,
        @Email @NotBlank String email
) {}
