package com.example.AccountsBS.controller;

//Spring Framework
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

//AccountsBS
import com.example.AccountsBS.dto.request.UpdateAccountBSRequestDto;
import com.example.AccountsBS.dto.response.AccountBSResponseDto;
import com.example.AccountsBS.model.AccountBS;
import com.example.AccountsBS.service.AccountBSService;
import com.example.AccountsBS.mapper.AccountBSMapper;

//Dependencies
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;

@Tag(name = "AccountBS", description = "Endpoints e operações relacionados ao gerenciamento de contas no sistema BS")
@RestController
@RequestMapping("bairroSeguro/accounts")
public class AccountBSController {

    private final AccountBSService accountBSService;
    private final AccountBSMapper accountBSMapper;

    public AccountBSController(AccountBSService accountBSService, AccountBSMapper accountBSMapper) {
        this.accountBSService = accountBSService;
        this.accountBSMapper = accountBSMapper;
    }

    @Operation(summary = "Lista as contas existentes")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Conta encontrada"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Acesso bloqueado por falta de autenticação",
                    content = @Content
            )
    })
    @GetMapping
    public ResponseEntity<List<AccountBSResponseDto>> list() {
        List<AccountBS> accounts = accountBSService.findAll();
        List<AccountBSResponseDto> accountResponseDtos = accountBSMapper.fromAccountBSToDto(accounts);
        return ResponseEntity.ok().body(accountResponseDtos);
    }

    @Operation(summary = "Busca uma conta pelo id informado")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Conta encontrada"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Acesso bloqueado por falta de autenticação",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Conta não encontrada",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<AccountBSResponseDto> findById(@PathVariable UUID id) {
        AccountBS account = accountBSService.findById(id);
        return ResponseEntity.ok().body(accountBSMapper.fromAccountBSToDto(account));
    }

    @Operation(summary = "Atualiza uma conta pelo id informado")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Conta atualizada"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Acesso bloqueado por falta de autenticação",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Conta não encontrada",
                    content = @Content
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<AccountBSResponseDto> update(@PathVariable UUID id, @RequestBody @Valid UpdateAccountBSRequestDto accountDto) {
        AccountBS account = accountBSMapper.fromUpdateAccountBSRequestDtoToEntity(accountDto);
        AccountBS updated = accountBSService.update(account, id);
        return ResponseEntity.ok().body(accountBSMapper.fromAccountBSToDto(updated));
    }

    @Operation(summary = "Apaga uma conta pelo id informado")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Conta apagada"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Acesso bloqueado por falta de autenticação",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Conta não encontrada",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        accountBSService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
