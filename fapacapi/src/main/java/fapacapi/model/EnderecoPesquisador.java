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
public class EnderecoPesquisador implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idEnderecoPesquisador;

    @Column(nullable = false)
    private String tipoEnderecoPesquisador;

    @Column
    private String telefoneEnderecoPesquisador;

    @Column
    private String cepEnderecoPesquisador;

    @Column
    private String logradouroEnderecoPesquisador;

    @Column
    private String numeroEnderecoPesquisador;

    @Column
    private String bairroEnderecoPesquisador;

    @Column
    private String estadoEnderecoPesquisador;

    @Column
    private String paisEnderecoPesquisador;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idPesquisador", nullable = false)
    private Pesquisador pesquisador;

    public Long getIdEnderecoPesquisador() {
        return idEnderecoPesquisador;
    }

    public void setIdEnderecoPesquisador(Long idEnderecoPesquisador) {
        this.idEnderecoPesquisador = idEnderecoPesquisador;
    }

    public String getTipoEnderecoPesquisador() {
        return tipoEnderecoPesquisador;
    }

    public void setTipoEnderecoPesquisador(String tipoEnderecoPesquisador) {
        this.tipoEnderecoPesquisador = tipoEnderecoPesquisador;
    }

    public String getTelefoneEnderecoPesquisador() {
        return telefoneEnderecoPesquisador;
    }

    public void setTelefoneEnderecoPesquisador(String telefoneEnderecoPesquisador) {
        this.telefoneEnderecoPesquisador = telefoneEnderecoPesquisador;
    }

    public String getCepEnderecoPesquisador() {
        return cepEnderecoPesquisador;
    }

    public void setCepEnderecoPesquisador(String cepEnderecoPesquisador) {
        this.cepEnderecoPesquisador = cepEnderecoPesquisador;
    }

    public String getLogradouroEnderecoPesquisador() {
        return logradouroEnderecoPesquisador;
    }

    public void setLogradouroEnderecoPesquisador(String logradouroEnderecoPesquisador) {
        this.logradouroEnderecoPesquisador = logradouroEnderecoPesquisador;
    }

    public String getNumeroEnderecoPesquisador() {
        return numeroEnderecoPesquisador;
    }

    public void setNumeroEnderecoPesquisador(String numeroEnderecoPesquisador) {
        this.numeroEnderecoPesquisador = numeroEnderecoPesquisador;
    }

    public String getBairroEnderecoPesquisador() {
        return bairroEnderecoPesquisador;
    }

    public void setBairroEnderecoPesquisador(String bairroEnderecoPesquisador) {
        this.bairroEnderecoPesquisador = bairroEnderecoPesquisador;
    }

    public String getEstadoEnderecoPesquisador() {
        return estadoEnderecoPesquisador;
    }

    public void setEstadoEnderecoPesquisador(String estadoEnderecoPesquisador) {
        this.estadoEnderecoPesquisador = estadoEnderecoPesquisador;
    }

    public String getPaisEnderecoPesquisador() {
        return paisEnderecoPesquisador;
    }

    public void setPaisEnderecoPesquisador(String paisEnderecoPesquisador) {
        this.paisEnderecoPesquisador = paisEnderecoPesquisador;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

}