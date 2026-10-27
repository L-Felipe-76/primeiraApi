package com.example.AccountsBS.dto.response;

//AccountsBS
import com.example.AccountsBS.model.Genero;

//Dependencies
import java.time.LocalDate;
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;

public record MoradorBSResponseDto(

    @Schema(description = "Id único do morador")
    UUID id,

    @Schema(description = "Nome do morador", example = "Lalo Oliveira")
    String nomeCompleto,

    @Schema(description = "Gênero do morador", example = "M")
    Genero genero,

    @Schema(description = "Data de nascimento do morador", example = "2020-01-22")
    LocalDate dataDeNascimento,

    @Schema(description = "Telefone do morador", example = "90000-0000")
    String telefone ) {
}
