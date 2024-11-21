package fapacapi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class AreaConhecimento implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idAreaConhecimento;

    @Column(nullable = false)
    private String nomeAreaConhecimento;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "primeira_sub_area_id")
    private List<PrimeiraSubArea> primeiraSubareas = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "segunda_sub_area_id")
    private List<SegundaSubArea> segundaSubareas = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "terceira_sub_area_id")
    private List<TerceiraSubArea> terceiraSubareas = new ArrayList<>();

    public Long getIdAreaConhecimento() {
        return idAreaConhecimento;
    }

    public void setIdAreaConhecimento(Long idAreaConhecimento) {
        this.idAreaConhecimento = idAreaConhecimento;
    }

    public String getNomeAreaConhecimento() {
        return nomeAreaConhecimento;
    }

    public void setNomeAreaConhecimento(String nomeAreaConhecimento) {
        this.nomeAreaConhecimento = nomeAreaConhecimento;
    }

    public List<PrimeiraSubArea> getPrimeiraSubareas() {
        return primeiraSubareas;
    }

    public void setPrimeiraSubareas(List<PrimeiraSubArea> primeiraSubareas) {
        this.primeiraSubareas = primeiraSubareas;
    }

    public List<SegundaSubArea> getSegundaSubareas() {
        return segundaSubareas;
    }

    public void setSegundaSubareas(List<SegundaSubArea> segundaSubareas) {
        this.segundaSubareas = segundaSubareas;
    }

    public List<TerceiraSubArea> getTerceiraSubareas() {
        return terceiraSubareas;
    }

    public void setTerceiraSubareas(List<TerceiraSubArea> terceiraSubareas) {
        this.terceiraSubareas = terceiraSubareas;
    }

}