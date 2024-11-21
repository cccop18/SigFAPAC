package fapacapi.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Pesquisador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idPesquisador;

    @Column(nullable = false)
    private String nomeCompletoPesquisador;

    @Column
    private String fotoPesquisador;

    @Column(nullable = false)
    private String emailPesquisador;

    @Column(nullable = false)
    private String sexoPesquisador;

    @Column(nullable = false)
    private LocalDate dataNascimentoPesquisador;

    @Column(nullable = false)
    private String corRaca;

    @Column(nullable = false)
    private String nomeMaePesquisador;

    @Column
    private String nomePaiPesquisador;

    @Column(nullable = false)
    private String curriculoLattesPesquisador;

    @Column(nullable = false)
    private String nivelAcademicoPesquisador;    
    
    @Column
    private String cpfPesquisador;

    @Column(nullable = false)
    private String senhaPesquisador;

    @Column(nullable = false)
    private String rgPesquisador;

    @Column
    private String orgaoEmissorPesquisador;

    @Column
    private String ufPesquisador;

    @Column
    private LocalDate dataEmissaoPesquisador;

    @Column
    private String passaportePesquisador;

    @Column
    private String passaporteFilePesquisador;

    @Column
    private boolean ativo = true;

    @Column(nullable = false)
    private String telefonePesquisador;

    @ManyToOne(optional = false)
    private TipoPesquisador tipoPesquisador;

    public Long getIdPesquisador() {
        return idPesquisador;
    }

    public void setIdPesquisador(Long idPesquisador) {
        this.idPesquisador = idPesquisador;
    }

    public String getNomeCompletoPesquisador() {
        return nomeCompletoPesquisador;
    }

    public void setNomeCompletoPesquisador(String nomeCompletoPesquisador) {
        this.nomeCompletoPesquisador = nomeCompletoPesquisador;
    }

    public String getFotoPesquisador() {
        return fotoPesquisador;
    }

    public void setFotoPesquisador(String fotoPesquisador) {
        this.fotoPesquisador = fotoPesquisador;
    }

    public String getEmailPesquisador() {
        return emailPesquisador;
    }

    public void setEmailPesquisador(String emailPesquisador) {
        this.emailPesquisador = emailPesquisador;
    }

    public String getSexoPesquisador() {
        return sexoPesquisador;
    }

    public void setSexoPesquisador(String sexoPesquisador) {
        this.sexoPesquisador = sexoPesquisador;
    }

    public LocalDate getDataNascimentoPesquisador() {
        return dataNascimentoPesquisador;
    }

    public void setDataNascimentoPesquisador(LocalDate dataNascimentoPesquisador) {
        this.dataNascimentoPesquisador = dataNascimentoPesquisador;
    }

    public String getCorRaca() {
        return corRaca;
    }

    public void setCorRaca(String corRaca) {
        this.corRaca = corRaca;
    }

    public String getNomeMaePesquisador() {
        return nomeMaePesquisador;
    }

    public void setNomeMaePesquisador(String nomeMaePesquisador) {
        this.nomeMaePesquisador = nomeMaePesquisador;
    }

    public String getNomePaiPesquisador() {
        return nomePaiPesquisador;
    }

    public void setNomePaiPesquisador(String nomePaiPesquisador) {
        this.nomePaiPesquisador = nomePaiPesquisador;
    }

    public String getCurriculoLattesPesquisador() {
        return curriculoLattesPesquisador;
    }

    public void setCurriculoLattesPesquisador(String curriculoLattesPesquisador) {
        this.curriculoLattesPesquisador = curriculoLattesPesquisador;
    }

    public String getNivelAcademicoPesquisador() {
        return nivelAcademicoPesquisador;
    }

    public void setNivelAcademicoPesquisador(String nivelAcademicoPesquisador) {
        this.nivelAcademicoPesquisador = nivelAcademicoPesquisador;
    }

    public String getCpfPesquisador() {
        return cpfPesquisador;
    }

    public void setCpfPesquisador(String cpfPesquisador) {
        this.cpfPesquisador = cpfPesquisador;
    }

    public String getSenhaPesquisador() {
        return senhaPesquisador;
    }

    public void setSenhaPesquisador(String senhaPesquisador) {
        this.senhaPesquisador = senhaPesquisador;
    }

    public String getRgPesquisador() {
        return rgPesquisador;
    }

    public void setRgPesquisador(String rgPesquisador) {
        this.rgPesquisador = rgPesquisador;
    }

    public String getOrgaoEmissorPesquisador() {
        return orgaoEmissorPesquisador;
    }

    public void setOrgaoEmissorPesquisador(String orgaoEmissorPesquisador) {
        this.orgaoEmissorPesquisador = orgaoEmissorPesquisador;
    }

    public String getUfPesquisador() {
        return ufPesquisador;
    }

    public void setUfPesquisador(String ufPesquisador) {
        this.ufPesquisador = ufPesquisador;
    }

    public LocalDate getDataEmissaoPesquisador() {
        return dataEmissaoPesquisador;
    }

    public void setDataEmissaoPesquisador(LocalDate dataEmissaoPesquisador) {
        this.dataEmissaoPesquisador = dataEmissaoPesquisador;
    }

    public String getPassaportePesquisador() {
        return passaportePesquisador;
    }

    public void setPassaportePesquisador(String passaportePesquisador) {
        this.passaportePesquisador = passaportePesquisador;
    }

    public String getPassaporteFilePesquisador() {
        return passaporteFilePesquisador;
    }

    public void setPassaporteFilePesquisador(String passaporteFilePesquisador) {
        this.passaporteFilePesquisador = passaporteFilePesquisador;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getTelefonePesquisador() {
        return telefonePesquisador;
    }

    public void setTelefonePesquisador(String telefonePesquisador) {
        this.telefonePesquisador = telefonePesquisador;
    }

    public TipoPesquisador getTipoPesquisador() {
        return tipoPesquisador;
    }

    public void setTipoPesquisador(TipoPesquisador tipoPesquisador) {
        this.tipoPesquisador = tipoPesquisador;
    }
    
}