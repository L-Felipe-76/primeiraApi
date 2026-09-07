package com.example.accounts.dto.request;

import jakarta.validation.constraints.*;

public record CreateAccountRequestDto(
    @NotBlank
    String email,

    @NotBlank
    String password) {

}
