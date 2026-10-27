package com.example.AccountsBS.exception;

//Dependencies
import java.util.UUID;

public class OcorrenciaNotFoundException extends RuntimeException {
    public OcorrenciaNotFoundException(UUID id) {
        super("Ocorrência not found: " + id);
    }
}
