package com.example.AccountsBS.dto.response;

//Dependencies
import io.swagger.v3.oas.annotations.media.Schema;

public record AuthResponseDto(

        @Schema(description = "Token de segurança JWT, usado para autenticação")
        String token
) {
}
