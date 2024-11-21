package fapacapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PropostaConhecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idPropostaConhecimento;

    @ManyToOne(optional = false)
    private Proposta proposta;

    @ManyToOne(optional = false)
    private AreaConhecimento areaConhecimento;

    @ManyToOne(optional = true)
    private PrimeiraSubArea primeiraSubArea;

    @ManyToOne(optional = true)
    private SegundaSubArea segundaSubArea;
    
    @ManyToOne(optional = true)
    private TerceiraSubArea terceiraSubArea;

    public Long getIdPropostaConhecimento() {
        return idPropostaConhecimento;
    }

    public void setIdPropostaConhecimento(Long idPropostaConhecimento) {
        this.idPropostaConhecimento = idPropostaConhecimento;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
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
