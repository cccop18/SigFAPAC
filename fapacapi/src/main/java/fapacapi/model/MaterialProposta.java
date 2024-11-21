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
public class MaterialProposta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idMaterialProposta;

    @Lob
    @Column(columnDefinition = "MEDIUMTEXT")
    private String especificacaoMaterialProposta;

    @Column
    private int quantidadeMaterialProposta;

    @Column
    private String medidaMaterialProposta;

    @Column
    private double custoUniMaterialProposta;

    @Column
    private String dataMaterialProposta;

    @ManyToOne(optional = false)
    private OrcamentoProposta orcamentoProposta;

    @ManyToOne(optional = false)
    private Moeda moeda;

    public Long getIdMaterialProposta() {
        return idMaterialProposta;
    }

    public void setIdMaterialProposta(Long idMaterialProposta) {
        this.idMaterialProposta = idMaterialProposta;
    }

    public String getEspecificacaoMaterialProposta() {
        return especificacaoMaterialProposta;
    }

    public void setEspecificacaoMaterialProposta(String especificacaoMaterialProposta) {
        this.especificacaoMaterialProposta = especificacaoMaterialProposta;
    }

    public int getQuantidadeMaterialProposta() {
        return quantidadeMaterialProposta;
    }

    public void setQuantidadeMaterialProposta(int quantidadeMaterialProposta) {
        this.quantidadeMaterialProposta = quantidadeMaterialProposta;
    }

    public String getMedidaMaterialProposta() {
        return medidaMaterialProposta;
    }

    public void setMedidaMaterialProposta(String medidaMaterialProposta) {
        this.medidaMaterialProposta = medidaMaterialProposta;
    }

    public double getCustoUniMaterialProposta() {
        return custoUniMaterialProposta;
    }

    public void setCustoUniMaterialProposta(double custoUniMaterialProposta) {
        this.custoUniMaterialProposta = custoUniMaterialProposta;
    }

    public String getDataMaterialProposta() {
        return dataMaterialProposta;
    }

    public void setDataMaterialProposta(String dataMaterialProposta) {
        this.dataMaterialProposta = dataMaterialProposta;
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
