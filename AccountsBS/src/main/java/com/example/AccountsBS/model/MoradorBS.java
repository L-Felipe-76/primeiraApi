package com.example.AccountsBS.model;

//Dependencies
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Table(name = "moradores_bs")
@Entity
public class MoradorBS {

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

    @Column(nullable = false, length = 10)
    private String telefone;

    public MoradorBS() {
    }

    public MoradorBS(String nomeCompleto, Genero genero, LocalDate dataDeNascimento, String telefone) {
        this.nomeCompleto = nomeCompleto;
        this.genero = genero;
        this.dataDeNascimento = dataDeNascimento;
        this.telefone = telefone;
    }

    public MoradorBS(UUID id, String nomeCompleto, Genero genero, LocalDate dataDeNascimento, String telefone) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.genero = genero;
        this.dataDeNascimento = dataDeNascimento;
        this.telefone = telefone;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
