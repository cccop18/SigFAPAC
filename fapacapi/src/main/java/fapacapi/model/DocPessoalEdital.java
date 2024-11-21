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
public class DocPessoalEdital implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idDocPessoalEdital;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idEdital", nullable = false)
    private Edital edital;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idPessoais", nullable = false)
    private DocPessoal pessoal;

    public Long getIdDocPessoalEdital() {
        return idDocPessoalEdital;
    }

    public void setIdDocPessoalEdital(Long idDocPessoalEdital) {
        this.idDocPessoalEdital = idDocPessoalEdital;
    }

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }

    public DocPessoal getPessoal() {
        return pessoal;
    }

    public void setPessoal(DocPessoal pessoal) {
        this.pessoal = pessoal;
    }

}