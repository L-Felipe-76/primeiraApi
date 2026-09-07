package com.example.accounts.dto.request;

import jakarta.validation.constraints.*;

public record UpdateAccountRequestDto(
        @NotBlank
        String email,

        @NotBlank
        String password) {

}
