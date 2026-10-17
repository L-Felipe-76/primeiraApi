package com.example.AccountsBS.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LogInAccountsBSRequestDto(

        @NotBlank(message = "O email é obrigatório")
        String email,

        @NotBlank(message = "A senha é obrigatória") @Size(min = 8, message = "A senha deve ter ao menos 8 caracteres")
        String password
) {
}
