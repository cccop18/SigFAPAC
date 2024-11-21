package fapacapi.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EnderecoUnidade implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idEnderecoUnidade;
    
    @Column(nullable = false)
    private String cepEnderecoUnidade;

    @Column(nullable = false)
    private String logradouroEnderecoUnidade;

    @Column(nullable = false)
    private String numeroEnderecoUnidade;

    @Column(nullable = false)
    private String bairroEnderecoUnidade;

    @Column(nullable = false)
    private String estadoEnderecoUnidade;

    @Column(nullable = false)
    private String paisEnderecoUnidade;

    public Long getIdEnderecoUnidade() {
        return idEnderecoUnidade;
    }

    public void setIdEnderecoUnidade(Long idEnderecoUnidade) {
        this.idEnderecoUnidade = idEnderecoUnidade;
    }

    public String getCepEnderecoUnidade() {
        return cepEnderecoUnidade;
    }

    public void setCepEnderecoUnidade(String cepEnderecoUnidade) {
        this.cepEnderecoUnidade = cepEnderecoUnidade;
    }

    public String getLogradouroEnderecoUnidade() {
        return logradouroEnderecoUnidade;
    }

    public void setLogradouroEnderecoUnidade(String logradouroEnderecoUnidade) {
        this.logradouroEnderecoUnidade = logradouroEnderecoUnidade;
    }

    public String getNumeroEnderecoUnidade() {
        return numeroEnderecoUnidade;
    }

    public void setNumeroEnderecoUnidade(String numeroEnderecoUnidade) {
        this.numeroEnderecoUnidade = numeroEnderecoUnidade;
    }

    public String getBairroEnderecoUnidade() {
        return bairroEnderecoUnidade;
    }

    public void setBairroEnderecoUnidade(String bairroEnderecoUnidade) {
        this.bairroEnderecoUnidade = bairroEnderecoUnidade;
    }

    public String getEstadoEnderecoUnidade() {
        return estadoEnderecoUnidade;
    }

    public void setEstadoEnderecoUnidade(String estadoEnderecoUnidade) {
        this.estadoEnderecoUnidade = estadoEnderecoUnidade;
    }

    public String getPaisEnderecoUnidade() {
        return paisEnderecoUnidade;
    }

    public void setPaisEnderecoUnidade(String paisEnderecoUnidade) {
        this.paisEnderecoUnidade = paisEnderecoUnidade;
    }

}