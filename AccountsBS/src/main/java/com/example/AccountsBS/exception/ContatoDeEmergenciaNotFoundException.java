package com.example.AccountsBS.exception;

//Dependency
import java.util.UUID;

public class ContatoDeEmergenciaNotFoundException extends RuntimeException {
    public ContatoDeEmergenciaNotFoundException(UUID id) {
        super("Telefone not found: " + id);
    }
}
