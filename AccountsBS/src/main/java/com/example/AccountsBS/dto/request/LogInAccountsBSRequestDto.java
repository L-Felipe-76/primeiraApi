package com.example.AccountsBS.dto.request;

//Dependencies
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

public record LogInAccountsBSRequestDto(

        @Schema(description = "Email da conta", example = "luiz@gmail.com")
        @NotBlank(message = "O email é obrigatório")
        String email,

        @Schema(description = "Senha da conta", example = "luiz1234")
        @NotBlank(message = "A senha é obrigatória") @Size(min = 8, message = "A senha deve ter ao menos 8 caracteres")
        String password
) {
}
