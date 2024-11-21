package fapacapi.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class PessoalProposta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idPessoalProposta;

    @Column(nullable = false)
    private String funcaoPessoalProposta;

    @Column(nullable = false)
    private String formacaoPessoalProposta;

    @Lob
    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    private String perfilPessoalProposta;

    @Column(nullable = false)
    private int periodoPessoalProposta;

    @Column(nullable = false)
    private String inicioPessoalPropostacol;

    @Column(nullable = false)
    private int horaSemanaPessoalProposta;

    @Column(nullable = false)
    private double custoHoraPessoalProposta;

    @Column(nullable = false)
    private double custoTotalPessoalPropostacol;

    @Column(nullable = false)
    private String dataPessoalProposta;

    @ManyToOne(optional = false)
    private OrcamentoProposta orcamentoProposta;

    public Long getIdPessoalProposta() {
        return idPessoalProposta;
    }

    public void setIdPessoalProposta(Long idPessoalProposta) {
        this.idPessoalProposta = idPessoalProposta;
    }

    public String getFuncaoPessoalProposta() {
        return funcaoPessoalProposta;
    }

    public void setFuncaoPessoalProposta(String funcaoPessoalProposta) {
        this.funcaoPessoalProposta = funcaoPessoalProposta;
    }

    public String getFormacaoPessoalProposta() {
        return formacaoPessoalProposta;
    }

    public void setFormacaoPessoalProposta(String formacaoPessoalProposta) {
        this.formacaoPessoalProposta = formacaoPessoalProposta;
    }

    public String getPerfilPessoalProposta() {
        return perfilPessoalProposta;
    }

    public void setPerfilPessoalProposta(String perfilPessoalProposta) {
        this.perfilPessoalProposta = perfilPessoalProposta;
    }

    public int getPeriodoPessoalProposta() {
        return periodoPessoalProposta;
    }

    public void setPeriodoPessoalProposta(int periodoPessoalProposta) {
        this.periodoPessoalProposta = periodoPessoalProposta;
    }

    public String getInicioPessoalPropostacol() {
        return inicioPessoalPropostacol;
    }

    public void setInicioPessoalPropostacol(String inicioPessoalPropostacol) {
        this.inicioPessoalPropostacol = inicioPessoalPropostacol;
    }

    public int getHoraSemanaPessoalProposta() {
        return horaSemanaPessoalProposta;
    }

    public void setHoraSemanaPessoalProposta(int horaSemanaPessoalProposta) {
        this.horaSemanaPessoalProposta = horaSemanaPessoalProposta;
    }

    public double getCustoHoraPessoalProposta() {
        return custoHoraPessoalProposta;
    }

    public void setCustoHoraPessoalProposta(double custoHoraPessoalProposta) {
        this.custoHoraPessoalProposta = custoHoraPessoalProposta;
    }

    public double getCustoTotalPessoalPropostacol() {
        return custoTotalPessoalPropostacol;
    }

    public void setCustoTotalPessoalPropostacol(double custoTotalPessoalPropostacol) {
        this.custoTotalPessoalPropostacol = custoTotalPessoalPropostacol;
    }

    public String getDataPessoalProposta() {
        return dataPessoalProposta;
    }

    public void setDataPessoalProposta(String dataPessoalProposta) {
        this.dataPessoalProposta = dataPessoalProposta;
    }

    public OrcamentoProposta getOrcamentoProposta() {
        return orcamentoProposta;
    }

    public void setOrcamentoProposta(OrcamentoProposta orcamentoProposta) {
        this.orcamentoProposta = orcamentoProposta;
    }

}
