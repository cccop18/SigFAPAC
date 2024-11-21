package fapacapi.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class ParceriasProposta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idParceriasProposta;

    @Column(nullable = false)
    private String entidadeParceriaProposta;

    @Column(nullable = false)
    private String tipoParceriaProposta;

    @Lob
    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    private String descricaoParceriaProposta;

    @ManyToOne(optional = false)
    private OrcamentoProposta orcamentoProposta;

    @ManyToOne(optional = false)
    private Moeda moeda;

    public Long getIdParceriasProposta() {
        return idParceriasProposta;
    }

    public void setIdParceriasProposta(Long idParceriasProposta) {
        this.idParceriasProposta = idParceriasProposta;
    }

    public String getEntidadeParceriaProposta() {
        return entidadeParceriaProposta;
    }

    public void setEntidadeParceriaProposta(String entidadeParceriaProposta) {
        this.entidadeParceriaProposta = entidadeParceriaProposta;
    }

    public String getTipoParceriaProposta() {
        return tipoParceriaProposta;
    }

    public void setTipoParceriaProposta(String tipoParceriaProposta) {
        this.tipoParceriaProposta = tipoParceriaProposta;
    }

    public String getDescricaoParceriaProposta() {
        return descricaoParceriaProposta;
    }

    public void setDescricaoParceriaProposta(String descricaoParceriaProposta) {
        this.descricaoParceriaProposta = descricaoParceriaProposta;
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
