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
public class FuncaoEdital implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idFuncaoEdital;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idEdital", nullable = false)
    private Edital edital;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idFuncao", nullable = false)
    private Funcao funcao;

    public Long getIdFuncaoEdital() {
        return idFuncaoEdital;
    }

    public void setIdFuncaoEdital(Long idFuncaoEdital) {
        this.idFuncaoEdital = idFuncaoEdital;
    }

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

}