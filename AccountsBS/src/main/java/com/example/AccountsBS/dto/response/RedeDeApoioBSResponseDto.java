package com.example.AccountsBS.dto.response;

//Dependencies
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;

public record RedeDeApoioBSResponseDto(

    @Schema(description = "Id único da rede de apoio")
    UUID id,

    @Schema(description = "Cep da rede de apoio", example = "00000-000")
    String cep,

    @Schema(description = "Estado da rede de apoio", example = "Alagoas")
    String estado,

    @Schema(description = "Cidade da rede de apoio", example = "Maceió")
    String cidade,

    @Schema(description = "Bairro da rede de apoio", example = "Farol")
    String bairro,

    @Schema(description = "Quantidade de integrantes da rede de apoio", example = "50")
    int integrantes ) {
}
