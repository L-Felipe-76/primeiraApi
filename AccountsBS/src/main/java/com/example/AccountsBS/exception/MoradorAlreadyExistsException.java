package com.example.AccountsBS.exception;

public class MoradorAlreadyExistsException extends RuntimeException {

    public MoradorAlreadyExistsException(String nomeCompleto) {
        super("Morador já existe: " + nomeCompleto);
    }
}
