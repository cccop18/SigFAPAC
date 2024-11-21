package fapacapi.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Funcao implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idFuncao;

    @Column(nullable = false)
    private String nomeFuncao;

    public Long getIdFuncao() {
        return idFuncao;
    }

    public String getNomeFuncao() {
        return nomeFuncao;
    }

    public void setNomeFuncao(String nomeFuncao) {
        this.nomeFuncao = nomeFuncao;
    }

    public void setIdFuncao(Long idFuncao) {
        this.idFuncao = idFuncao;
    }



    public boolean isAtivoFuncao() {
        return ativoFuncao;
    }

    public void setAtivoFuncao(boolean ativoFuncao) {
        this.ativoFuncao = ativoFuncao;
    }

    @Column(nullable = false)
    private boolean ativoFuncao;

}