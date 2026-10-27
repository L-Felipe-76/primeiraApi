package com.example.AccountsBS.exception;

//Dependencies
import java.util.UUID;

public class MoradorNotFoundException extends RuntimeException {
    public MoradorNotFoundException(UUID id) {
        super("Morador not found: " + id);
    }
}
