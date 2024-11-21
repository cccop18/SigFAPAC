package fapacapi.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ServicoProposta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idServicoProposta;

    @Column(nullable = false)
    private String tipoServicoProposta;

    @Column(nullable = false)
    private String especificacaoServicoProposta;
    
    @Column(nullable = false)
    private String custoServicoProposta;

    @Column(nullable = false)
    private String dataServicoProposta;

    @ManyToOne(optional = false)
    private OrcamentoProposta orcamentoProposta;

    @ManyToOne(optional = false)
    private Moeda moeda;

    public Long getIdServicoProposta() {
        return idServicoProposta;
    }

    public void setIdServicoProposta(Long idServicoProposta) {
        this.idServicoProposta = idServicoProposta;
    }

    public String getTipoServicoProposta() {
        return tipoServicoProposta;
    }

    public void setTipoServicoProposta(String tipoServicoProposta) {
        this.tipoServicoProposta = tipoServicoProposta;
    }

    public String getEspecificacaoServicoProposta() {
        return especificacaoServicoProposta;
    }

    public void setEspecificacaoServicoProposta(String especificacaoServicoProposta) {
        this.especificacaoServicoProposta = especificacaoServicoProposta;
    }

    public String getCustoServicoProposta() {
        return custoServicoProposta;
    }

    public void setCustoServicoProposta(String custoServicoProposta) {
        this.custoServicoProposta = custoServicoProposta;
    }

    public String getDataServicoProposta() {
        return dataServicoProposta;
    }

    public void setDataServicoProposta(String dataServicoProposta) {
        this.dataServicoProposta = dataServicoProposta;
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