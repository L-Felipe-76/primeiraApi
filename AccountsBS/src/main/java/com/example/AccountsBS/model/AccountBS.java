package com.example.AccountsBS.model;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;


@Entity
public class AccountBS {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 250)
    private String nomeCompleto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private Genero genero;

    @Column(nullable = false)
    private LocalDate dataDeNascimento;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 10)
    private String telefone;

    @Column(nullable = false, length = 9)
    private String cep;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(nullable = false, length = 250)
    private String cidade;

    @Column(nullable = false, length = 250)
    private String bairro;

    @Column(nullable = false, length = 250)
    private String rua;

    @Column(nullable = false, length = 5)
    private String numeroCasa;

    @Column(length = 2000)
    private String referenciasRua;

    public AccountBS() {
    }

    public AccountBS(String nomeCompleto, Genero genero, LocalDate dataDeNascimento, String email, String password, String telefone, String cep, String estado, String cidade, String bairro, String rua, String numeroCasa, String referenciasRua) {
        this.nomeCompleto = nomeCompleto;
        this.genero = genero;
        this.dataDeNascimento = dataDeNascimento;
        this.email = email;
        this.password = password;
        this.telefone = telefone;
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numeroCasa = numeroCasa;
        this.referenciasRua = referenciasRua;
    }

    public AccountBS(UUID id, String nomeCompleto, Genero genero, LocalDate dataDeNascimento, String email, String password, String telefone, String cep, String estado, String cidade, String bairro, String rua, String numeroCasa, String referenciasRua) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.genero = genero;
        this.dataDeNascimento = dataDeNascimento;
        this.email = email;
        this.password = password;
        this.telefone = telefone;
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numeroCasa = numeroCasa;
        this.referenciasRua = referenciasRua;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getReferenciasRua() {
        return referenciasRua;
    }

    public void setReferenciasRua(String referenciasRua) {
        this.referenciasRua = referenciasRua;
    }
}
