package com.example.AccountsBS.exception;

public class RedeDeApoioAlreadyExistsException extends RuntimeException {
    public RedeDeApoioAlreadyExistsException(String cep, String Bairro) {
        super("Rede de apoio already exists: " + cep + " - " + Bairro);
    }
}
