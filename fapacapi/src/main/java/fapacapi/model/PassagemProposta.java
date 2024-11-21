package fapacapi.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PassagemProposta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idPassagemProposta;

    @Column(nullable = false)
    private String tipoPassagemProposta;

    @Column(nullable = false)
    private String origemPassagemProposta;

    @Column(nullable = false)
    private String destinoPassagemProposta;

    @Column(nullable = false)
    private boolean idaVoltaPassagemProposta;

    @Column(nullable = false)
    private int quantidadePassagemProposta;

    // Esse campo tá repetido no modelo do banco de dados, erro de repetição ou é um outro campo?
    // @Column(nullable = false)
    // private String tipoPassagemProposta;

    @Column(nullable = false)
    private double custoUnitarioPassagemProposta;

    @Column(nullable = false)
    private String dataPassagemProposta;

    @ManyToOne(optional = false)
    private OrcamentoProposta orcamentoProposta;

    @ManyToOne(optional = false)
    private Moeda moeda;

    public Long getIdPassagemProposta() {
        return idPassagemProposta;
    }

    public void setIdPassagemProposta(Long idPassagemProposta) {
        this.idPassagemProposta = idPassagemProposta;
    }

    public String getTipoPassagemProposta() {
        return tipoPassagemProposta;
    }

    public void setTipoPassagemProposta(String tipoPassagemProposta) {
        this.tipoPassagemProposta = tipoPassagemProposta;
    }

    public String getOrigemPassagemProposta() {
        return origemPassagemProposta;
    }

    public void setOrigemPassagemProposta(String origemPassagemProposta) {
        this.origemPassagemProposta = origemPassagemProposta;
    }

    public String getDestinoPassagemProposta() {
        return destinoPassagemProposta;
    }

    public void setDestinoPassagemProposta(String destinoPassagemProposta) {
        this.destinoPassagemProposta = destinoPassagemProposta;
    }

    public boolean isIdaVoltaPassagemProposta() {
        return idaVoltaPassagemProposta;
    }

    public void setIdaVoltaPassagemProposta(boolean idaVoltaPassagemProposta) {
        this.idaVoltaPassagemProposta = idaVoltaPassagemProposta;
    }

    public int getQuantidadePassagemProposta() {
        return quantidadePassagemProposta;
    }

    public void setQuantidadePassagemProposta(int quantidadePassagemProposta) {
        this.quantidadePassagemProposta = quantidadePassagemProposta;
    }

    public double getCustoUnitarioPassagemProposta() {
        return custoUnitarioPassagemProposta;
    }

    public void setCustoUnitarioPassagemProposta(double custoUnitarioPassagemProposta) {
        this.custoUnitarioPassagemProposta = custoUnitarioPassagemProposta;
    }

    public String getDataPassagemProposta() {
        return dataPassagemProposta;
    }

    public void setDataPassagemProposta(String dataPassagemProposta) {
        this.dataPassagemProposta = dataPassagemProposta;
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