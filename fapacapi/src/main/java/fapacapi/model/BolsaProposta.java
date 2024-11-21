package fapacapi.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class BolsaProposta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idBolsaProposta;

    @Column(nullable = false)
    private String modalidadeBolsaProposta;

    @Column(nullable = false)
    private int quantidadeBolsaProposta;

    @Column(nullable = false)
    private String duracaoBolsaProposta;

    @Column(nullable = false)
    private double bolsaMesBolsaProposta;

    @Column(nullable = false)
    private String areaBolsaProposta;

    @ManyToOne(optional = false)
    private OrcamentoProposta orcamentoProposta;

    @ManyToOne(optional = false)
    private Moeda moeda;

    public Long getIdBolsaProposta() {
        return idBolsaProposta;
    }

    public void setIdBolsaProposta(Long idBolsaProposta) {
        this.idBolsaProposta = idBolsaProposta;
    }

    public String getModalidadeBolsaProposta() {
        return modalidadeBolsaProposta;
    }

    public void setModalidadeBolsaProposta(String modalidadeBolsaProposta) {
        this.modalidadeBolsaProposta = modalidadeBolsaProposta;
    }

    public int getQuantidadeBolsaProposta() {
        return quantidadeBolsaProposta;
    }

    public void setQuantidadeBolsaProposta(int quantidadeBolsaProposta) {
        this.quantidadeBolsaProposta = quantidadeBolsaProposta;
    }

    public String getDuracaoBolsaProposta() {
        return duracaoBolsaProposta;
    }

    public void setDuracaoBolsaProposta(String duracaoBolsaProposta) {
        this.duracaoBolsaProposta = duracaoBolsaProposta;
    }

    public double getBolsaMesBolsaProposta() {
        return bolsaMesBolsaProposta;
    }

    public void setBolsaMesBolsaProposta(double bolsaMesBolsaProposta) {
        this.bolsaMesBolsaProposta = bolsaMesBolsaProposta;
    }

    public String getAreaBolsaProposta() {
        return areaBolsaProposta;
    }

    public void setAreaBolsaProposta(String areaBolsaProposta) {
        this.areaBolsaProposta = areaBolsaProposta;
    }

    public OrcamentoProposta getOrcamentoProposta() {
        return orcamentoProposta;
    }

    public void setOrcamentoProposta(OrcamentoProposta orcamentoProposta) {
        this.orcamentoProposta = orcamentoProposta;
    }

    public Moeda getMoeda() {
        return moeda;
    }

    public void setMoeda(Moeda moeda) {
        this.moeda = moeda;
    }

}