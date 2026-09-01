package com.example.accounts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.util.UUID;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String password;

    public Account() {
    }

    public Account(String email,String password) {
        this.email = email;
        this.password = password;
    }

    public Account(UUID id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}