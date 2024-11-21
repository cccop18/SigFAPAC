package fapacapi.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FaixaFinanciamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idFaixaFinanciamento;

    @Column
    private String nomeFaixaFinanciamento;

    @Column
    private double minFaixaFinanciamento;

    @Column
    private double maxFaixaFinanciamento;

    @Column
    private String observacaoFaixaFinanciamento;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idEdital", nullable = false)
    private Edital edital;

    public Long getIdFaixaFinanciamento() {
        return idFaixaFinanciamento;
    }

    public void setIdFaixaFinanciamento(Long idFaixaFinanciamento) {
        this.idFaixaFinanciamento = idFaixaFinanciamento;
    }

    public String getNomeFaixaFinanciamento() {
        return nomeFaixaFinanciamento;
    }

    public void setNomeFaixaFinanciamento(String nomeFaixaFinanciamento) {
        this.nomeFaixaFinanciamento = nomeFaixaFinanciamento;
    }

    public Double getMinFaixaFinanciamento() {
        return minFaixaFinanciamento;
    }

    public void setMinFaixaFinanciamento(Double minFaixaFinanciamento) {
        this.minFaixaFinanciamento = minFaixaFinanciamento;
    }

    public Double getMaxFaixaFinanciamento() {
        return maxFaixaFinanciamento;
    }

    public void setMaxFaixaFinanciamento(Double maxFaixaFinanciamento) {
        this.maxFaixaFinanciamento = maxFaixaFinanciamento;
    }

    public String getObservacaoFaixaFinanciamento() {
        return observacaoFaixaFinanciamento;
    }

    public void setObservacaoFaixaFinanciamento(String observacaoFaixaFinanciamento) {
        this.observacaoFaixaFinanciamento = observacaoFaixaFinanciamento;
    }

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }

}
