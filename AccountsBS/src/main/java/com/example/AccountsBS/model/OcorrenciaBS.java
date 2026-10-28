package com.example.AccountsBS.model;

//Dependencies
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "ocorrencias_bs")
@Entity
public class OcorrenciaBS {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 250)
    private String tituloOcorrencia;

    @Column(nullable = false, length = 2000)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Tag tag;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @PrePersist
    public void prePersist() {
        this.dataHora = LocalDateTime.now();
    }

    public OcorrenciaBS() {
    }

    public OcorrenciaBS(String tituloOcorrencia, String descricao, Tag tag) {
        this.tituloOcorrencia = tituloOcorrencia;
        this.descricao = descricao;
        this.tag = tag;
    }

    public OcorrenciaBS(UUID id, String tituloOcorrencia, String descricao, Tag tag) {
        this.id = id;
        this.tituloOcorrencia = tituloOcorrencia;
        this.descricao = descricao;
        this.tag = tag;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTituloOcorrencia() {
        return tituloOcorrencia;
    }

    public void setTituloOcorrencia(String tituloOcorrencia) {
        this.tituloOcorrencia = tituloOcorrencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
