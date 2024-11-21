package fapacapi.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PesquisadorConhecimento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idPesquisadorConhecimento;

    @ManyToOne(optional = false)
    private Pesquisador pesquisador;

    @ManyToOne(optional = false)
    private AreaConhecimento areaConhecimento;

    @ManyToOne(optional = true)
    private PrimeiraSubArea primeiraSubArea;

    @ManyToOne(optional = true)
    private SegundaSubArea segundaSubArea;

    @ManyToOne(optional = true)
    private TerceiraSubArea terceiraSubArea;

    public Long getIdPesquisadorConhecimento() {
        return idPesquisadorConhecimento;
    }

    public void setIdPesquisadorConhecimento(Long idPesquisadorConhecimento) {
        this.idPesquisadorConhecimento = idPesquisadorConhecimento;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

    public AreaConhecimento getAreaConhecimento() {
        return areaConhecimento;
    }

    public void setAreaConhecimento(AreaConhecimento areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    public PrimeiraSubArea getPrimeiraSubArea() {
        return primeiraSubArea;
    }

    public void setPrimeiraSubArea(PrimeiraSubArea primeiraSubArea) {
        this.primeiraSubArea = primeiraSubArea;
    }

    public SegundaSubArea getSegundaSubArea() {
        return segundaSubArea;
    }

    public void setSegundaSubArea(SegundaSubArea segundaSubArea) {
        this.segundaSubArea = segundaSubArea;
    }

    public TerceiraSubArea getTerceiraSubArea() {
        return terceiraSubArea;
    }

    public void setTerceiraSubArea(TerceiraSubArea terceiraSubArea) {
        this.terceiraSubArea = terceiraSubArea;
    }

}