package com.example.AccountsBS.controller;

import com.example.AccountsBS.dto.request.CreateAccountBSRequestDto;
import com.example.AccountsBS.dto.request.UpdateAccountBSRequestDto;
import com.example.AccountsBS.dto.response.AccountBSResponseDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import com.example.AccountsBS.model.AccountBS;
import com.example.AccountsBS.service.AccountBSService;
import com.example.AccountsBS.mapper.AccountBSMapper;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("bairroSeguro/accounts")
public class AccountBSController {

    private final AccountBSService accountBSService;
    private final AccountBSMapper accountBSMapper;

    public AccountBSController(AccountBSService accountBSService, AccountBSMapper accountBSMapper) {
        this.accountBSService = accountBSService;
        this.accountBSMapper = accountBSMapper;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateAccountBSRequestDto accountDto) {
        AccountBS account = accountBSMapper.fromCreateAccountBSRequestDtoToEntity(accountDto);
        UUID uuid =accountBSService.create(account);
        URI location = URI.create("/accounts/" + uuid);
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<AccountBSResponseDto>> list() {
        List<AccountBS> accounts = accountBSService.findAll();
        List<AccountBSResponseDto> accountResponseDtos = accountBSMapper.fromAccountBSToDto(accounts);
        return ResponseEntity.ok().body(accountResponseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountBSResponseDto> findById(@PathVariable UUID id) {
        AccountBS account = accountBSService.findById(id);
        return ResponseEntity.ok().body(accountBSMapper.fromAccountBSToDto(account));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountBSResponseDto> update(@PathVariable UUID id, @RequestBody @Valid UpdateAccountBSRequestDto accountDto) {
        AccountBS account = accountBSMapper.fromUpdateAccountBSRequestDtoToEntity(accountDto);
        AccountBS updated = accountBSService.update(account, id);
        return ResponseEntity.ok().body(accountBSMapper.fromAccountBSToDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        accountBSService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
