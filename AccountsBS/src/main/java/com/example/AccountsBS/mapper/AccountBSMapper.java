package com.example.AccountsBS.mapper;

import com.example.AccountsBS.dto.request.CreateAccountBSRequestDto;
import com.example.AccountsBS.dto.request.UpdateAccountBSRequestDto;
import com.example.AccountsBS.dto.response.AccountBSResponseDto;
import org.springframework.stereotype.Component;
import com.example.AccountsBS.model.AccountBS;

import java.util.List;

@Component
public class AccountBSMapper {

    public AccountBS fromCreateAccountBSRequestDtoToEntity(CreateAccountBSRequestDto accountDto) {
        return new AccountBS(
                accountDto.nomeCompleto(),
                accountDto.genero(),
                accountDto.dataDeNascimento(),
                accountDto.email(),
                accountDto.password(),
                accountDto.telefone(),
                accountDto.cep(),
                accountDto.estado(),
                accountDto.cidade(),
                accountDto.bairro(),
                accountDto.rua(),
                accountDto.numeroCasa(),
                accountDto.referenciasRua()
        );
    }

    public AccountBS fromUpdateAccountBSRequestDtoToEntity(UpdateAccountBSRequestDto accountDto) {
        return new AccountBS(
                accountDto.nomeCompleto(),
                accountDto.genero(),
                accountDto.dataDeNascimento(),
                accountDto.email(),
                accountDto.password(),
                accountDto.telefone(),
                accountDto.cep(),
                accountDto.estado(),
                accountDto.cidade(),
                accountDto.bairro(),
                accountDto.rua(),
                accountDto.numeroCasa(),
                accountDto.referenciasRua()
        );
    }

    public AccountBSResponseDto fromAccountBSToDto(AccountBS account) {
        return new AccountBSResponseDto(
                account.getId(),
                account.getNomeCompleto(),
                account.getGenero(),
                account.getDataDeNascimento(),
                account.getEmail(),
                account.getTelefone(),
                account.getCep(),
                account.getEstado(),
                account.getCidade(),
                account.getBairro(),
                account.getRua(),
                account.getNumeroCasa(),
                account.getReferenciasRua()
        );
    }

    public List<AccountBSResponseDto> fromAccountBSToDto(List<AccountBS> accounts) {
        return accounts.stream().map(this::fromAccountBSToDto).toList();
    }
}
