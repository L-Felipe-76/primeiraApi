package com.example.AccountsBS.dto.request;

//AccountsBS
import com.example.AccountsBS.model.Genero;

//Dependencies
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados necessários para criar um morador")
public record CreateMoradorBSRequestDto(

        @Schema(description = "Nome do morador", example = "Lalo Oliveira")
        @NotBlank(message = "O nome é obrigatório") @Size(min = 7, max = 250, message = "O nome completo deve conter ao menos 7 caracteres")
        String nomeCompleto,

        @Schema(description = "Gênero do morador", example = "M")
        @NotNull(message = "O gênero é obrigatório")
        Genero genero,

        @Schema(description = "Data de nascimento do morador", example = "2026-01-22")
        @NotNull(message = "A data de nascimento é obrigatória") @PastOrPresent
        LocalDate dataDeNascimento,

        @Schema(description = "Telefone do morador", example = "90000-0000")
        @NotBlank(message = "O telefone é obrigatório") @Size(min = 10, max = 10, message = "O telefone deve seguir o padrão 9XXXX-XXXX (Obs:Incluir hífen)")
        String telefone ) {
}
