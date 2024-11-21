package fapacapi.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Retificacoes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idRetificacoes;

    @Column(nullable = false)
    private String nomeRetificacoes;

    @Column
    private String fileRetificacoes;

    @Column
    private LocalDateTime dataRetificacoes;

    @Column(nullable = false)
    private boolean ativoRetificacoes;

    @ManyToOne(optional = false)
    private Edital edital;

    public Long getIdRetificacoes() {
        return idRetificacoes;
    }

    public void setIdRetificacoes(Long idRetificacoes) {
        this.idRetificacoes = idRetificacoes;
    }

    public String getNomeRetificacoes() {
        return nomeRetificacoes;
    }

    public void setNomeRetificacoes(String nomeRetificacoes) {
        this.nomeRetificacoes = nomeRetificacoes;
    }

    public LocalDateTime getDataRetificacoes() {
        return dataRetificacoes;
    }
    public void setDataRetificacoes(LocalDateTime dataRetificacoes) {
        this.dataRetificacoes = dataRetificacoes;
    }
    public String getFileRetificacoes() {
        return fileRetificacoes;
    }

    public void setFileRetificacoes(String fileRetificacoes) {
        this.fileRetificacoes = fileRetificacoes;
    }

    public boolean getAtivoRetificacoes() {
        return ativoRetificacoes;
    }

    public void setAtivoRetificacoes(boolean ativoRetificacoes) {
        this.ativoRetificacoes = ativoRetificacoes;
    }

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }
    
}