package fapacapi.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Programa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idPrograma;

    @Column(nullable = false)
    private String nomePrograma;

    @Column(nullable = false)
    private boolean ativoPrograma;

    public Long getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Long idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getNomePrograma() {
        return nomePrograma;
    }

    public void setNomePrograma(String nomePrograma) {
        this.nomePrograma = nomePrograma;
    }
    
    public boolean isAtivoPrograma() {
        return ativoPrograma;
    }

    public void setAtivoPrograma(boolean ativoPrograma) {
        this.ativoPrograma = ativoPrograma;
    }

}