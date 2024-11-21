package fapacapi.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class SegundaSubArea implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idSegundaSubArea;  // Esse será o único campo de ID

    @Column(nullable = false)
    private String nomeSegundaSub;

    @ManyToOne(optional = false)
    private PrimeiraSubArea primeiraSubArea;

    public Long getIdSegundaSubArea() {
        return idSegundaSubArea;
    }

    public void setIdSegundaSubArea(Long idSegundaSubArea) {
        this.idSegundaSubArea = idSegundaSubArea;
    }

    public String getNomeSegundaSub() {
        return nomeSegundaSub;
    }

    public void setNomeSegundaSub(String nomeSegundaSub) {
        this.nomeSegundaSub = nomeSegundaSub;
    }

    public PrimeiraSubArea getPrimeiraSubArea() {
        return primeiraSubArea;
    }

    public void setPrimeiraSubArea(PrimeiraSubArea primeiraSubArea) {
        this.primeiraSubArea = primeiraSubArea;
    }

}