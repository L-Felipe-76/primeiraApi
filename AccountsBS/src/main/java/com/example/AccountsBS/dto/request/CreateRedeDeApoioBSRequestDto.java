package com.example.AccountsBS.dto.request;

//Dependencies
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados necessários para criar uma rede de apoio")
public record CreateRedeDeApoioBSRequestDto(

    @Schema(description = "Cep da rede de apoio", example = "00000-000")
    @NotBlank(message = "O cep é obrigatório") @Size(min = 9, max = 9, message = "O cep deve seguir o padrão XXXXX-XXX (Obs:Incluir hífen)")
    String cep,

    @Schema(description = "Estado da rede de apoio", example = "Alagoas")
    @NotBlank(message = "O estado é obrigatório") @Size(max = 20)
    String estado,

    @Schema(description = "Cidade da rede de apoio", example = "Maceió")
    @NotBlank(message = "A cidade é obrigatória") @Size(max = 250)
    String cidade,

    @Schema(description = "Bairro da rede de apoio", example = "Farol")
    @NotBlank(message = "O bairro é obrigatório") @Size(max = 250)
    String bairro,

    @Schema(description = "Quantidade de integrantes da rede de apoio", example = "50")
    @NotBlank(message = "A quantidade de integrantes é obrigatória")
    int integrantes ) {
}
