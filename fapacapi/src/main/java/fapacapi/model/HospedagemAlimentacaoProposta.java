package fapacapi.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class HospedagemAlimentacaoProposta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idHospedagemAlimentacaoProposta;

    @Column(nullable = false)
    private String paisHospedagemAlimentacaoProposta;

    @Column(nullable = false)
    private String estadoHospedagemAliementacaoProposta;

    @Column(nullable = false)
    private String hopedagemAlimentacaoPropostacol;

    @Column(nullable = false)
    private String municipioHospedagemAlimentacaoProposta;

    @Column(nullable = false)
    private String provinciaHospedagemAlimentacaoProposta;

    @Column(nullable = false)
    private String especificacaoHospedagemAlimentacaoProposta;

    @Column(nullable = false)
    private int quantidadeHospedagemAlimentacaoProposta;

    @Column(nullable = false)
    private Long custoHospedagemAlimentacaoProposta;

    @Column(nullable = false)
    private String dataHospedagemAlimentacaoProposta;

    @ManyToOne(optional = false)
    private OrcamentoProposta orcamentoProposta;

    @ManyToOne(optional = false)
    private Moeda moeda;

    public Long getIdHospedagemAlimentacaoProposta() {
        return idHospedagemAlimentacaoProposta;
    }

    public void setIdHospedagemAlimentacaoProposta(Long idHospedagemAlimentacaoProposta) {
        this.idHospedagemAlimentacaoProposta = idHospedagemAlimentacaoProposta;
    }

    public String getPaisHospedagemAlimentacaoProposta() {
        return paisHospedagemAlimentacaoProposta;
    }

    public void setPaisHospedagemAlimentacaoProposta(String paisHospedagemAlimentacaoProposta) {
        this.paisHospedagemAlimentacaoProposta = paisHospedagemAlimentacaoProposta;
    }

    public String getEstadoHospedagemAliementacaoProposta() {
        return estadoHospedagemAliementacaoProposta;
    }

    public void setEstadoHospedagemAliementacaoProposta(String estadoHospedagemAliementacaoProposta) {
        this.estadoHospedagemAliementacaoProposta = estadoHospedagemAliementacaoProposta;
    }

    public String getHopedagemAlimentacaoPropostacol() {
        return hopedagemAlimentacaoPropostacol;
    }

    public void setHopedagemAlimentacaoPropostacol(String hopedagemAlimentacaoPropostacol) {
        this.hopedagemAlimentacaoPropostacol = hopedagemAlimentacaoPropostacol;
    }

    public String getMunicipioHospedagemAlimentacaoProposta() {
        return municipioHospedagemAlimentacaoProposta;
    }

    public void setMunicipioHospedagemAlimentacaoProposta(String municipioHospedagemAlimentacaoProposta) {
        this.municipioHospedagemAlimentacaoProposta = municipioHospedagemAlimentacaoProposta;
    }

    public String getProvinciaHospedagemAlimentacaoProposta() {
        return provinciaHospedagemAlimentacaoProposta;
    }

    public void setProvinciaHospedagemAlimentacaoProposta(String provinciaHospedagemAlimentacaoProposta) {
        this.provinciaHospedagemAlimentacaoProposta = provinciaHospedagemAlimentacaoProposta;
    }

    public String getEspecificacaoHospedagemAlimentacaoProposta() {
        return especificacaoHospedagemAlimentacaoProposta;
    }

    public void setEspecificacaoHospedagemAlimentacaoProposta(String especificacaoHospedagemAlimentacaoProposta) {
        this.especificacaoHospedagemAlimentacaoProposta = especificacaoHospedagemAlimentacaoProposta;
    }

    public int getQuantidadeHospedagemAlimentacaoProposta() {
        return quantidadeHospedagemAlimentacaoProposta;
    }

    public void setQuantidadeHospedagemAlimentacaoProposta(int quantidadeHospedagemAlimentacaoProposta) {
        this.quantidadeHospedagemAlimentacaoProposta = quantidadeHospedagemAlimentacaoProposta;
    }

    public Long getCustoHospedagemAlimentacaoProposta() {
        return custoHospedagemAlimentacaoProposta;
    }

    public void setCustoHospedagemAlimentacaoProposta(Long custoHospedagemAlimentacaoProposta) {
        this.custoHospedagemAlimentacaoProposta = custoHospedagemAlimentacaoProposta;
    }

    public String getDataHospedagemAlimentacaoProposta() {
        return dataHospedagemAlimentacaoProposta;
    }

    public void setDataHospedagemAlimentacaoProposta(String dataHospedagemAlimentacaoProposta) {
        this.dataHospedagemAlimentacaoProposta = dataHospedagemAlimentacaoProposta;
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
