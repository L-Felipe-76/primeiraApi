package com.example.AccountsBS.dto.request;

//AccountsBS
import com.example.AccountsBS.model.Genero;

//Dependencies
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados necessários para criar uma conta.")
public record RegisterAccountBSRequestDto(

    @Schema(description = "Nome do usuário", example = "Luiz Felipe")
    @NotBlank(message = "O nome é obrigatório") @Size(min = 7, max = 250, message = "O nome completo deve conter ao menos 7 caracteres")
    String nomeCompleto,

    @Schema(description = "Gênero do usuário", example = "M")
    @NotNull(message = "O gênero é obrigatório")
    Genero genero,

    @Schema(description = "Data de nascimento do usuário", example = "2026-10-18")
    @NotNull(message = "A data de nascimento é obrigatória") @PastOrPresent
    LocalDate dataDeNascimento,

    @Schema(description = "Email da conta", example = "luiz@gmail.com")
    @NotBlank(message = "O email é obrigatório")
    String email,

    @Schema(description = "Senha da conta", example = "luiz1234")
    @NotBlank(message = "A senha é obrigatória") @Size(min = 8, message = "A senha deve ter ao menos 8 caracteres")
    String password,

    @Schema(description = "Telefone do usuário", example = "99999-9999")
    @NotBlank(message = "O telefone é obrigatório") @Size(min = 10, max = 10, message = "O telefone deve seguir o padrão 9XXXX-XXXX (Obs:Incluir hífen)")
    String telefone,

    @Schema(description = "Cep do usuário", example = "00000-000")
    @NotBlank(message = "O cep é obrigatório") @Size(min = 9, max = 9, message = "O cep deve seguir o padrão XXXXX-XXX (Obs:Incluir hífen)")
    String cep,

    @Schema(description = "Estado do usuário", example = "Alagoas")
    @NotBlank(message = "O estado é obrigatório") @Size(max = 20)
    String estado,

    @Schema(description = "Cidade do usuário", example = "Maceió")
    @NotBlank(message = "A cidade é obrigatória") @Size(max = 250)
    String cidade,

    @Schema(description = "Bairro do usuário", example = "Farol")
    @NotBlank(message = "O bairro é obrigatório") @Size(max = 250)
    String bairro,

    @Schema(description = "Rua do usuário", example = "Rua Cônego Machado")
    @NotBlank(message = "A rua é obrigatória") @Size(max = 250)
    String rua,

    @Schema(description = "Número da casa do usuário", example = "21")
    @NotBlank(message = "O número da casa é obrigatório") @Size(max = 5)
    String numeroCasa,

    @Schema(description = "Referências da rua/casa do usuário", example = "Mesma rua da faculdade Cesmac, casa logo ao lado da biblioteca do Cesmac")
    @Size(max = 2000)
    String referenciasRua ) {

}
