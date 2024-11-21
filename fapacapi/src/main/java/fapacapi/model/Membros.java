package fapacapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Membros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idMembros;

    @ManyToOne(optional = false)
    private Proposta proposta;

    @ManyToOne(optional = false)
    private Funcao funcao;

    @ManyToOne(optional = false)
    private Pesquisador pesquisador;

    public Long getIdMembros() {
        return idMembros;
    }

    public void setIdMembros(Long idMembros) {
        this.idMembros = idMembros;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }
    
    
}
