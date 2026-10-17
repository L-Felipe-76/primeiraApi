package com.example.AccountsBS.mapper;

//Spring Framework
import org.springframework.stereotype.Component;

//AccountsBS
import com.example.AccountsBS.dto.request.CreateAccountBSRequestDto;
import com.example.AccountsBS.dto.request.UpdateAccountBSRequestDto;
import com.example.AccountsBS.dto.response.AccountBSResponseDto;
import com.example.AccountsBS.model.AccountBS;

//Dependencies
import java.util.List;

@Component
public class AccountBSMapper {

    public AccountBS fromCreateAccountBSRequestDtoToEntity(CreateAccountBSRequestDto accountDto) {
        return AccountBS.builder()
                .nomeCompleto(accountDto.nomeCompleto())
                .genero(accountDto.genero())
                .dataDeNascimento(accountDto.dataDeNascimento())
                .email(accountDto.email())
                .password(accountDto.password())
                .telefone(accountDto.telefone())
                .cep(accountDto.cep())
                .estado(accountDto.estado())
                .cidade(accountDto.cidade())
                .bairro(accountDto.bairro())
                .rua(accountDto.rua())
                .numeroCasa(accountDto.numeroCasa())
                .referenciasRua(accountDto.referenciasRua())
                .build();
    }

    public AccountBS fromUpdateAccountBSRequestDtoToEntity(UpdateAccountBSRequestDto accountDto) {
        return AccountBS.builder()
                .nomeCompleto(accountDto.nomeCompleto())
                .genero(accountDto.genero())
                .dataDeNascimento(accountDto.dataDeNascimento())
                .email(accountDto.email())
                .password(accountDto.password())
                .telefone(accountDto.telefone())
                .cep(accountDto.cep())
                .estado(accountDto.estado())
                .cidade(accountDto.cidade())
                .bairro(accountDto.bairro())
                .rua(accountDto.rua())
                .numeroCasa(accountDto.numeroCasa())
                .referenciasRua(accountDto.referenciasRua())
                .build();
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
