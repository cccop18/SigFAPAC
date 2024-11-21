package fapacapi.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DocPessoal implements Serializable{
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idPessoais;
  
    @Column(nullable = false)
    private String nomeDocPessoal;

    @Column
    private boolean ativoDocPessoal = true;

    public boolean isAtivo() {
        return ativoDocPessoal;
    }

    public void setAtivo(boolean ativoDocPessoal) {
        this.ativoDocPessoal = ativoDocPessoal;
    }

    public Long getIdPessoais() {
        return idPessoais;
    }

    public void setIdPessoais(Long idPessoais) {
        this.idPessoais = idPessoais;
    }

    public String getNomeDocPessoal() {
        return nomeDocPessoal;
    }

    public void setNomeDocPessoal(String nomeDocPessoal) {
        this.nomeDocPessoal = nomeDocPessoal;
    }
}