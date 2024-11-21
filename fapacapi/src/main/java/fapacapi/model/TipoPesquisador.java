package fapacapi.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TipoPesquisador implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idTipoPesquisador;

    @Column(nullable = false)
    private String descricaoTipoPesquisador;

    public Long getIdTipoPesquisador() {
        return idTipoPesquisador;
    }

    public void setIdTipoPesquisador(Long idTipoPesquisador) {
        this.idTipoPesquisador = idTipoPesquisador;
    }

    public String getDescricaoTipoPesquisador() {
        return descricaoTipoPesquisador;
    }

    public void setDescricaoTipoPesquisador(String descricaoTipoPesquisador) {
        this.descricaoTipoPesquisador = descricaoTipoPesquisador;
    }
    
}