package com.example.AccountsBS.dto.response;

//Dependencies
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;

public record ContatoDeEmergenciaBSResponseDto(

    @Schema(description = "Id único do usuário")
    UUID id,

    @Schema(description = "Nome do contato de emergência", example = "SAMU")
    String nomeCompleto,

    @Schema(description = "Telefone do contato de emergência", example = "192")
    String telefone,

    @Schema(description = "Descrição do número de emergência", example = "Numero de emergência da SAMU indicado ligar em caso de emergências médicas e acidentes graves.")
    String descricao
) {
}
