package com.example.AccountsBS.dto.response;

import com.example.AccountsBS.model.Genero;

import java.time.LocalDate;
import java.util.UUID;

public record AccountBSResponseDto(
    UUID id,
    String nomeCompleto,
    Genero genero,
    LocalDate dataDeNascimento,
    String email,
    String telefone,
    String cep,
    String estado,
    String cidade,
    String bairro,
    String rua,
    String numeroCasa,
    String referenciasRua ) {
}
