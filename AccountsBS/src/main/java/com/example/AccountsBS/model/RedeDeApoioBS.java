package com.example.AccountsBS.model;

//Dependencies
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class RedeDeApoioBS {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 9)
    private String cep;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(nullable = false, length = 250)
    private String cidade;

    @Column(nullable = false, length = 250)
    private String bairro;

    @Column(nullable = false)
    private int integrantes;

    public RedeDeApoioBS() {
    }

    public RedeDeApoioBS(String cep, String estado, String cidade, String bairro, int integrantes) {
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.integrantes = integrantes;
    }

    public RedeDeApoioBS(UUID id, String cep, String estado, String cidade, String bairro, int integrantes) {
        this.id = id;
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.integrantes = integrantes;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(int integrantes) {
        this.integrantes = integrantes;
    }
}
