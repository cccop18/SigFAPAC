package fapacapi.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class UnidadeInstituicao implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idUnidadeInstituicao;

    @Column(nullable = false)
    private String nomeUnidade;

    @Column(nullable = false)
    private String emailUnidade;

    @Column(nullable = false)
    private String telefoneUnidade;

    @OneToOne
    @JoinColumn(name = "idEnderecoUnidade", nullable = false) // Define o nome da coluna para a chave estrangeira
    private EnderecoUnidade enderecoUnidade;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idInstituicao")
    private Instituicao instituicao;

    public Long getIdUnidadeInstituicao() {
        return idUnidadeInstituicao;
    }

    public void setIdUnidadeInstituicao(Long idUnidadeInstituicao) {
        this.idUnidadeInstituicao = idUnidadeInstituicao;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public String getEmailUnidade() {
        return emailUnidade;
    }

    public void setEmailUnidade(String emailUnidade) {
        this.emailUnidade = emailUnidade;
    }

    public String getTelefoneUnidade() {
        return telefoneUnidade;
    }

    public void setTelefoneUnidade(String telefoneUnidade) {
        this.telefoneUnidade = telefoneUnidade;
    }

    public EnderecoUnidade getEnderecoUnidade() {
        return enderecoUnidade;
    }

    public void setEnderecoUnidade(EnderecoUnidade enderecoUnidade) {
        this.enderecoUnidade = enderecoUnidade;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

}