package com.example.AccountsBS.dto.request;

import com.example.AccountsBS.model.Genero;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateAccountBSRequestDto(

    @NotBlank(message = "O nome é obrigatório") @Size(min = 7, max = 250, message = "O nome completo deve conter ao menos 7 caracteres")
    String nomeCompleto,

    @NotNull(message = "O gênero é obrigatório")
    Genero genero,

    @NotNull(message = "A data de nascimento é obrigatória") @PastOrPresent
    LocalDate dataDeNascimento,

    @NotBlank(message = "O email é obrigatório")
    String email,

    @NotBlank(message = "A senha é obrigatória")
    String password,

    @NotBlank(message = "O telefone é obrigatório") @Size(min = 10, max = 10, message = "O telefone deve seguir o padrão 9XXXX-XXXX (Obs:Incluir hífen)")
    String telefone,

    @NotBlank(message = "O cep é obrigatório") @Size(min = 9, max = 9, message = "O cep deve seguir o padrão XXXXX-XXX (Obs:Incluir hífen)")
    String cep,

    @NotBlank(message = "O estado é obrigatório") @Size(max = 20)
    String estado,

    @NotBlank(message = "A cidade é obrigatória") @Size(max = 250)
    String cidade,

    @NotBlank(message = "O bairro é obrigatório") @Size(max = 250)
    String bairro,

    @NotBlank(message = "A rua é obrigatória") @Size(max = 250)
    String rua,

    @NotBlank(message = "O número da casa é obrigatório") @Size(max = 5)
    String numeroCasa,

    @Size(max = 2000)
    String referenciasRua) {
}
