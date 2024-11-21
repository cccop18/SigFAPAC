package fapacapi.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Diarias implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idDiaria;

    @Column(nullable = false)
    private String tipoDiaria;

    @Column(nullable = false)
    private String nivelAcademicoDiaria;

    @Column(nullable = false)
    private String valorDiaria;

    @Column(nullable = false)
    private Boolean ativo = true;

    public Long getIdDiaria() {
        return idDiaria;
    }

    public void setIdDiaria(Long idDiaria) {
        this.idDiaria = idDiaria;
    }

    public String getTipoDiaria() {
        return tipoDiaria;
    }

    public void setTipoDiaria(String tipoDiaria) {
        this.tipoDiaria = tipoDiaria;
    }

    public String getNivelAcademicoDiaria() {
        return nivelAcademicoDiaria;
    }

    public void setNivelAcademicoDiaria(String nivelAcademicoDiaria) {
        this.nivelAcademicoDiaria = nivelAcademicoDiaria;
    }

    public String getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(String valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
}