package fapacapi.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Moeda implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idMoeda;

    @Column(nullable = false)
    private String nomeMoeda;

    @Column(nullable = false)
    private boolean ativaMoeda;

    public Long getIdMoeda() {
        return idMoeda;
    }

    public void setIdMoeda(Long idMoeda) {
        this.idMoeda = idMoeda;
    }

    public String getNomeMoeda() {
        return nomeMoeda;
    }

    public void setNomeMoeda(String nomeMoeda) {
        this.nomeMoeda = nomeMoeda;
    }

    public boolean isAtivaMoeda() {
        return ativaMoeda;
    }

    public void setAtivaMoeda(boolean ativaMoeda) {
        this.ativaMoeda = ativaMoeda;
    }

}