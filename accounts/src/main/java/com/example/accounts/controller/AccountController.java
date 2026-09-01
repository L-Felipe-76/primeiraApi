package com.example.accounts.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import com.example.accounts.model.Account;
import com.example.accounts.service.AccountService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("create")
    public ResponseEntity<Void> create(@RequestBody @Valid Account account) {
        UUID uuid =accountService.create(account);
        URI location = URI.create("Account" + uuid);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("list")
    public ResponseEntity<List<Account>> list() {
        List<Account> accounts = accountService.findAll();
        return ResponseEntity.ok().body(accounts);
    }
}
