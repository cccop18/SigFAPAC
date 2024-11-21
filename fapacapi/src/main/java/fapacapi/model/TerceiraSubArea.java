package fapacapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TerceiraSubArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeTerceiraSub;  

    @ManyToOne
    @JoinColumn(name = "segunda_sub_area_id_segunda_sub_area", nullable = false)
    private SegundaSubArea segundaSubArea;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeTerceiraSub() {
        return nomeTerceiraSub;
    }

    public void setNomeTerceiraSub(String nomeTerceiraSub) {
        this.nomeTerceiraSub = nomeTerceiraSub;
    }

    public SegundaSubArea getSegundaSubArea() {
        return segundaSubArea;
    }

    public void setSegundaSubArea(SegundaSubArea segundaSubArea) {
        this.segundaSubArea = segundaSubArea;
    }

}