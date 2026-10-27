package com.example.AccountsBS.exception;

public class ContatoDeEmergenciaAlreadyExistsException extends RuntimeException {
    public ContatoDeEmergenciaAlreadyExistsException(String telefone) {
        super("Telefone already exists: " + telefone);
    }
}
