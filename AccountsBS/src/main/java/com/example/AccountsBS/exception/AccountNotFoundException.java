package com.example.AccountsBS.exception;

//Dependencies
import java.util.UUID;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(UUID id) {
        super("Account not found: " + id);
    }
}

