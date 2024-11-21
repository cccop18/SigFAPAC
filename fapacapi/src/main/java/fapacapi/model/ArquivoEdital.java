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
public class ArquivoEdital implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idArquivoEdital;
  
    @ManyToOne(optional = false)
    @JoinColumn(name = "idArquivo", nullable = false)
    private Arquivo arquivo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idEdital", nullable = false)
    private Edital edital;

    public Long getIdArquivoEdital() {
        return idArquivoEdital;
    }

    public void setIdArquivoEdital(Long idArquivoEdital) {
        this.idArquivoEdital = idArquivoEdital;
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }

}