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
public class VinculoInstitucional implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idVinculoInstitucional;

    @Column(nullable = false)
    private String vinculoInstitucional;

    @Column(nullable = false)
    private Boolean vinculoEmpregaticio;

    @Column(nullable = false)
    private String tempoServico;

    @Column(nullable = false)
    private String regimeTrabalho;

    @Column(nullable = false)
    private String funcaoCargo;

    @Column(nullable = false)
    private String tempoDaFuncao;

    // Na modelagem de dados esse campo é obrigatório
    @ManyToOne(optional = true)
    private Pesquisador pesquisador;

    @ManyToOne(optional = false)
    private Instituicao instituicao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idUnidadeInstituicao")
    private UnidadeInstituicao unidadeInstituicao;

    public Long getIdVinculoInstitucional() {
        return idVinculoInstitucional;
    }

    public void setIdVinculoInstitucional(Long idVinculoInstitucional) {
        this.idVinculoInstitucional = idVinculoInstitucional;
    }

    public String getVinculoInstitucional() {
        return vinculoInstitucional;
    }

    public void setVinculoInstitucional(String vinculoInstitucional) {
        this.vinculoInstitucional = vinculoInstitucional;
    }

    public Boolean getVinculoEmpregaticio() {
        return vinculoEmpregaticio;
    }

    public void setVinculoEmpregaticio(Boolean vinculoEmpregaticio) {
        this.vinculoEmpregaticio = vinculoEmpregaticio;
    }

    public String getTempoServico() {
        return tempoServico;
    }

    public void setTempoServico(String tempoServico) {
        this.tempoServico = tempoServico;
    }

    public String getRegimeTrabalho() {
        return regimeTrabalho;
    }

    public void setRegimeTrabalho(String regimeTrabalho) {
        this.regimeTrabalho = regimeTrabalho;
    }

    public String getFuncaoCargo() {
        return funcaoCargo;
    }

    public void setFuncaoCargo(String funcaoCargo) {
        this.funcaoCargo = funcaoCargo;
    }

    public String getTempoDaFuncao() {
        return tempoDaFuncao;
    }

    public void setTempoDaFuncao(String tempoDaFuncao) {
        this.tempoDaFuncao = tempoDaFuncao;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public UnidadeInstituicao getUnidadeInstituicao() {
        return unidadeInstituicao;
    }

    public void setUnidadeInstituicao(UnidadeInstituicao unidadeInstituicao) {
        this.unidadeInstituicao = unidadeInstituicao;
    }

}