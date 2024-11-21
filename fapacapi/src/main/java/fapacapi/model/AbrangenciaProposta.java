package fapacapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class AbrangenciaProposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idAbrangenciaProposta;

    @Column(nullable = false)
    private String municipioAbrangenciaProposta;

    @ManyToOne(optional = false)
    private Proposta proposta;

    @ManyToOne(optional = false)
    private  Estado estado;

    public Long getIdAbrangenciaProposta() {
        return idAbrangenciaProposta;
    }

    public void setIdAbrangenciaProposta(Long idAbrangenciaProposta) {
        this.idAbrangenciaProposta = idAbrangenciaProposta;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getMunicipioAbrangenciaProposta() {
        return municipioAbrangenciaProposta;
    }

    public void setMunicipioAbrangenciaProposta(String municipioAbrangenciaProposta) {
        this.municipioAbrangenciaProposta = municipioAbrangenciaProposta;
    }
    
}
