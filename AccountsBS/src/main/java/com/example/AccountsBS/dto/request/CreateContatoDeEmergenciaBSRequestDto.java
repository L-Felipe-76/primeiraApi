package com.example.AccountsBS.dto.request;

//Dependencies
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados necessários para criar um contato de emergência")
public record CreateContatoDeEmergenciaBSRequestDto(

    @Schema(description = "Nome do contato de emergência", example = "SAMU")
    @NotBlank(message = "O nome é obrigatório") @Size(max = 250, message = "O nome completo máximo de 250 caracteres")
    String nomeCompleto,

    @Schema(description = "Telefone do contato de emergência", example = "192")
    @NotBlank(message = "O telefone é obrigatório") @Size(max = 10, message = "O telefone deve seguir o padrão 9XXXX-XXXX (Obs:Incluir hífen)")
    String telefone,

    @Schema(description = "Descrição do número de emergência", example = "Numero de emergência da SAMU indicado ligar em caso de emergências médicas e acidentes graves.")
    @NotBlank(message = "A descrição é obrigatória")
    String descricao ) {
}
