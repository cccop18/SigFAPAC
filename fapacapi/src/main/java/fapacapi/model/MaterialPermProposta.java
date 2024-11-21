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
public class MaterialPermProposta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idMaterialPermProposta;

    @Lob // Para armazenar dados textuais e o requisito de case sensitivity não ser relevante
    @Column(nullable = false)
    private String especificacaoMaterialPermProposta;

    @Column(nullable = false)
    private String tipoMaterialPermProposta;
    
    @Column(nullable = false)
    private int quantidadeMaterialPermProposta;

    @Column(nullable = false)
    private double custoMaterialPermProposta;

    @Column(nullable = false)
    private String dataMaterialPermProposta;

    @ManyToOne(optional = false)
    private OrcamentoProposta orcamentoProposta;

    @ManyToOne(optional = false)
    private Moeda moeda;

    public Long getIdMaterialPermProposta() {
        return idMaterialPermProposta;
    }

    public void setIdMaterialPermProposta(Long idMaterialPermProposta) {
        this.idMaterialPermProposta = idMaterialPermProposta;
    }

    public String getEspecificacaoMaterialPermProposta() {
        return especificacaoMaterialPermProposta;
    }

    public void setEspecificacaoMaterialPermProposta(String especificacaoMaterialPermProposta) {
        this.especificacaoMaterialPermProposta = especificacaoMaterialPermProposta;
    }

    public String getTipoMaterialPermProposta() {
        return tipoMaterialPermProposta;
    }

    public void setTipoMaterialPermProposta(String tipoMaterialPermProposta) {
        this.tipoMaterialPermProposta = tipoMaterialPermProposta;
    }

    public int getQuantidadeMaterialPermProposta() {
        return quantidadeMaterialPermProposta;
    }

    public void setQuantidadeMaterialPermProposta(int quantidadeMaterialPermProposta) {
        this.quantidadeMaterialPermProposta = quantidadeMaterialPermProposta;
    }

    public double getCustoMaterialPermProposta() {
        return custoMaterialPermProposta;
    }

    public void setCustoMaterialPermProposta(double custoMaterialPermProposta) {
        this.custoMaterialPermProposta = custoMaterialPermProposta;
    }

    public String getDataMaterialPermProposta() {
        return dataMaterialPermProposta;
    }

    public void setDataMaterialPermProposta(String dataMaterialPermProposta) {
        this.dataMaterialPermProposta = dataMaterialPermProposta;
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
