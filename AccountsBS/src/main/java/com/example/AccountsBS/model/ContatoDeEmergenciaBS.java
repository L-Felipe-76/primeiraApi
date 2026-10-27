package com.example.AccountsBS.model;

//Dependencies
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class ContatoDeEmergenciaBS {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 250)
    private String nomeCompleto;

    @Column(nullable = false, length = 10)
    private String telefone;

    @Column(nullable = false, length = 2000)
    private String descricao;

    public ContatoDeEmergenciaBS() {
    }

    public ContatoDeEmergenciaBS(String nomeCompleto, String telefone, String descricao) {
        this.nomeCompleto = nomeCompleto;
        this.telefone = telefone;
        this.descricao = descricao;
    }

    public ContatoDeEmergenciaBS(UUID id, String nomeCompleto, String telefone, String descricao) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.telefone = telefone;
        this.descricao = descricao;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}