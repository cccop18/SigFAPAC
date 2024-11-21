package fapacapi.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DiariaProposta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idDiariaProposta;

    @Column(nullable = false)
    private String tipoLocalidadeDiariaProposta;

    @Column(nullable = false)
    private String paisDiariaProposta;

    @Column(nullable = false)
    private String estadoDiariaProposta;

    @Column
    private String municipioDiariaProposta;

    @Column
    private String provinciaDiariaProposta;

    @Column(nullable = false)
    private double numeroDiariaProposta;

    @Column(nullable = false)
    private double custoUnitDiariaProposta;

    @Column
    private String data;

    @Column
    private String justicativa;

    @ManyToOne(optional = false)
    private OrcamentoProposta orcamentoProposta;

    @ManyToOne(optional = false)
    private Moeda moeda;

    public Long getIdDiariaProposta() {
        return idDiariaProposta;
    }

    public void setIdDiariaProposta(Long idDiariaProposta) {
        this.idDiariaProposta = idDiariaProposta;
    }

    public String getTipoLocalidadeDiariaProposta() {
        return tipoLocalidadeDiariaProposta;
    }

    public void setTipoLocalidadeDiariaProposta(String tipoLocalidadeDiariaProposta) {
        this.tipoLocalidadeDiariaProposta = tipoLocalidadeDiariaProposta;
    }

    public String getPaisDiariaProposta() {
        return paisDiariaProposta;
    }

    public void setPaisDiariaProposta(String paisDiariaProposta) {
        this.paisDiariaProposta = paisDiariaProposta;
    }

    public String getEstadoDiariaProposta() {
        return estadoDiariaProposta;
    }

    public void setEstadoDiariaProposta(String estadoDiariaProposta) {
        this.estadoDiariaProposta = estadoDiariaProposta;
    }

    public String getMunicipioDiariaProposta() {
        return municipioDiariaProposta;
    }

    public void setMunicipioDiariaProposta(String municipioDiariaProposta) {
        this.municipioDiariaProposta = municipioDiariaProposta;
    }

    public String getProvinciaDiariaProposta() {
        return provinciaDiariaProposta;
    }

    public void setProvinciaDiariaProposta(String provinciaDiariaProposta) {
        this.provinciaDiariaProposta = provinciaDiariaProposta;
    }

    public double getNumeroDiariaProposta() {
        return numeroDiariaProposta;
    }

    public void setNumeroDiariaProposta(double numeroDiariaProposta) {
        this.numeroDiariaProposta = numeroDiariaProposta;
    }

    public double getCustoUnitDiariaProposta() {
        return custoUnitDiariaProposta;
    }

    public void setCustoUnitDiariaProposta(double custoUnitDiariaProposta) {
        this.custoUnitDiariaProposta = custoUnitDiariaProposta;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getJusticativa() {
        return justicativa;
    }

    public void setJusticativa(String justicativa) {
        this.justicativa = justicativa;
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
