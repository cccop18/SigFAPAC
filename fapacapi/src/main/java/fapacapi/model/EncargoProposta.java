package fapacapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class EncargoProposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idEncargoProposta;

    @Column(nullable = false)
    private String especificacaoEncargoProposta;

    @Column(nullable = false)
    private float custoTotalProposta;

    @ManyToOne(optional = false)
    private OrcamentoProposta orcamentoProposta;

    @ManyToOne(optional = false)
    private Moeda moeda;

    public Long getIdEncargoProposta() {
        return idEncargoProposta;
    }

    public void setIdEncargoProposta(Long idEncargoProposta) {
        this.idEncargoProposta = idEncargoProposta;
    }

    public String getEspecificacaoEncargoProposta() {
        return especificacaoEncargoProposta;
    }

    public void setEspecificacaoEncargoProposta(String especificacaoEncargoProposta) {
        this.especificacaoEncargoProposta = especificacaoEncargoProposta;
    }

    public float getCustoTotalProposta() {
        return custoTotalProposta;
    }

    public void setCustoTotalProposta(float custoTotalProposta) {
        this.custoTotalProposta = custoTotalProposta;
    }

    public OrcamentoProposta getOrcamentoProposta() {
        return orcamentoProposta;
    }

    public void setOrcamentoProposta(OrcamentoProposta orcamentoProposta) {
        this.orcamentoProposta = orcamentoProposta;
    }

    public Moeda getMoeda() {
        return moeda;
    }

    public void setMoeda(Moeda moeda) {
        this.moeda = moeda;
    }

    
    
}
