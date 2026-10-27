package com.example.AccountsBS.exception;

//Dependencies
import java.util.UUID;

public class RedeDeApoioNotFoundException extends RuntimeException {
    public RedeDeApoioNotFoundException(UUID id) {
        super("Rede de apoio not found: " + id);
    }
}
