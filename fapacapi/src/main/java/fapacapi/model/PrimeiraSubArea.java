package fapacapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PrimeiraSubArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomePrimeiraSub;

    @ManyToOne
    private AreaConhecimento areaConhecimento;  // Relacionamento com AreaConhecimento

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePrimeiraSub() {
        return nomePrimeiraSub;
    }

    public void setNomePrimeiraSub(String nomePrimeiraSub) {
        this.nomePrimeiraSub = nomePrimeiraSub;
    }

    public AreaConhecimento getAreaConhecimento() {
        return areaConhecimento;
    }

    public void setAreaConhecimento(AreaConhecimento areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }
    
}