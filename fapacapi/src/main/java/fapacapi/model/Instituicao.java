package fapacapi.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Instituicao implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = false)
    private Long idInstituicao;

    @Column(nullable = false)
    private String nomeInstituicao;

    @Column(nullable = false)
    private String siglaInstituicao;

    @Column
    private String cnpjInstituicao;

    @Column
    private Boolean estrangeiraInstituicao;

    @Column
    private String paisInstituicao;

    @Column(nullable = false)
    private String dependenciaAdmInstituicao;

    @Column(nullable = false)
    private Boolean ensinoSuperiorInstituicao;

    @Column(nullable = false)
    private Boolean finsLucrativosLnstituicao;

    public Long getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(Long idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

    public String getSiglaInstituicao() {
        return siglaInstituicao;
    }

    public void setSiglaInstituicao(String siglaInstituicao) {
        this.siglaInstituicao = siglaInstituicao;
    }

    public String getCnpjInstituicao() {
        return cnpjInstituicao;
    }

    public void setCnpjInstituicao(String cnpjInstituicao) {
        this.cnpjInstituicao = cnpjInstituicao;
    }

    public Boolean getEstrangeiraInstituicao() {
        return estrangeiraInstituicao;
    }

    public void setEstrangeiraInstituicao(Boolean estrangeiraInstituicao) {
        this.estrangeiraInstituicao = estrangeiraInstituicao;
    }

    public String getPaisInstituicao() {
        return paisInstituicao;
    }

    public void setPaisInstituicao(String paisInstituicao) {
        this.paisInstituicao = paisInstituicao;
    }

    public String getDependenciaAdmInstituicao() {
        return dependenciaAdmInstituicao;
    }

    public void setDependenciaAdmInstituicao(String dependenciaAdmInstituicao) {
        this.dependenciaAdmInstituicao = dependenciaAdmInstituicao;
    }

    public Boolean getEnsinoSuperiorInstituicao() {
        return ensinoSuperiorInstituicao;
    }

    public void setEnsinoSuperiorInstituicao(Boolean ensinoSuperiorInstituicao) {
        this.ensinoSuperiorInstituicao = ensinoSuperiorInstituicao;
    }

    public Boolean getFinsLucrativosLnstituicao() {
        return finsLucrativosLnstituicao;
    }

    public void setFinsLucrativosLnstituicao(Boolean finsLucrativosLnstituicao) {
        this.finsLucrativosLnstituicao = finsLucrativosLnstituicao;
    }

}