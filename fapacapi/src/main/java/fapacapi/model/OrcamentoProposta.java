package fapacapi.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrcamentoProposta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idOrcamentoProposta;

    public Long getIdOrcamentoProposta() {
        return idOrcamentoProposta;
    }

    public void setIdOrcamentoProposta(Long idOrcamentoProposta) {
        this.idOrcamentoProposta = idOrcamentoProposta;
    }

}
