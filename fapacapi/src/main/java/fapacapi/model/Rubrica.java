package fapacapi.model;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Rubrica implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idRubrica;

    @Column(nullable = false)
    private int codigoRubrica;

    @Column(nullable = false)
    private String nomeRubrica;

    @Column(nullable = false)
    private String tipoRubrica;

    @Column(nullable = false)
    private boolean ativoRubrica;

    public Long getIdRubrica() {
        return idRubrica;
    }

    public void setIdRubrica(Long idRubrica) {
        this.idRubrica = idRubrica;
    }

    public int getCodigoRubrica() {
        return codigoRubrica;
    }

    public void setCodigoRubrica(int codigoRubrica) {
        this.codigoRubrica = codigoRubrica;
    }

    public String getNomeRubrica() {
        return nomeRubrica;
    }

    public void setNomeRubrica(String nomeRubrica) {
        this.nomeRubrica = nomeRubrica;
    }

    public String getTipoRubrica() {
        return tipoRubrica;
    }

    public void setTipoRubrica(String tipoRubrica) {
        this.tipoRubrica = tipoRubrica;
    }

    public Boolean getAtivoRubrica() {
        return ativoRubrica;
    }

    public void setAtivoRubrica(Boolean ativoRubrica) {
        this.ativoRubrica = ativoRubrica;
    }

}