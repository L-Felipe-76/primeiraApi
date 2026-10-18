package com.example.AccountsBS.dto.response;

//AccountsBS
import com.example.AccountsBS.model.Genero;

//Dependencies
import java.time.LocalDate;
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;

public record AccountBSResponseDto(

    @Schema(description = "Id único do usuário")
    UUID id,

    @Schema(description = "Nome do usuário", example = "Luiz Felipe")
    String nomeCompleto,

    @Schema(description = "Gênero do usuário", example = "M")
    Genero genero,

    @Schema(description = "Data de nascimento do usuário", example = "2026-10-18")
    LocalDate dataDeNascimento,

    @Schema(description = "Email da conta", example = "luiz@gmail.com")
    String email,

    @Schema(description = "Telefone do usuário", example = "99999-9999")
    String telefone,

    @Schema(description = "Cep do usuário", example = "00000-000")
    String cep,

    @Schema(description = "Estado do usuário", example = "Alagoas")
    String estado,

    @Schema(description = "Cidade do usuário", example = "Maceió")
    String cidade,

    @Schema(description = "Bairro do usuário", example = "Farol")
    String bairro,

    @Schema(description = "Rua do usuário", example = "Rua Cônego Machado")
    String rua,

    @Schema(description = "Número da casa do usuário", example = "21")
    String numeroCasa,

    @Schema(description = "Referências da rua/casa do usuário", example = "Mesma rua da faculdade Cesmac, casa logo ao lado da biblioteca do Cesmac")
    String referenciasRua ) {
}
