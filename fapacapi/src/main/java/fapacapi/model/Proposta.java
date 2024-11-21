package fapacapi.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Proposta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idProposta;

    @Column(nullable = false)
    private String tituloProposta;

    @Column
    private String grupoPesquisa;

    @Column(nullable = false)
    private LocalDate dataInicioPesquisa;

    @Column(nullable = false)
    private String duracaoProposta;

    @Column(nullable = false)
    private boolean termoAceiteProposta;

    @Column
    private boolean patenteProposta;

    @Column
    private boolean inovacaoProposta;

    @Column
    private boolean eticaProposta;

    @Column
    private String resumoProposta;

    @Column
    private String palavraChaveProposta;

    @Column
    private String sinteseProposta;

    @Column
    private String expCordProposta;

    @Column
    private String objetivoGeralProposta;

    @Column
    private String objetivoEspProposta;

    @Column
    private String metodologiaProposta;

    @Column
    private String metodosMatProposta;

    @Column
    private String resultsProposta;

    @Column
    private String impactosProposta;

    @Column
    private String impactosCienProposta;

    @Column
    private String impactosTechProposta;

    // Os campos entre os comentarios estão no BD como LONGBLOB

    @Column
    private String impactosSocialProposta;

    @Column
    private String impactosAmbientalProposta;

    @Column
    private String refBlibProposta;

    @Column
    private String estadoArteProposta;

    @Column
    private String justCopInterProposta;

    @Column
    private String qualiParceriasProposta;

    @Column
    private String obrasInstalProposta;

    // Final do comentario contendo os LONGBLOB

    @Column
    private String estadoProposta;

    @Column(nullable = false)
    private String situacao;

    @ManyToOne(optional = true)
    private FaixaFinanciamento faixaFinanciamento;

    @ManyToOne(optional = false)
    private Edital edital;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idPesquisador")
    private Pesquisador pesquisador;

    @OneToOne(optional = true)
    private OrcamentoProposta orcamentoProposta;

    public Long getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(Long idProposta) {
        this.idProposta = idProposta;
    }

    public String getTituloProposta() {
        return tituloProposta;
    }

    public void setTituloProposta(String tituloProposta) {
        this.tituloProposta = tituloProposta;
    }

    public String getGrupoPesquisa() {
        return grupoPesquisa;
    }

    public void setGrupoPesquisa(String grupoPesquisa) {
        this.grupoPesquisa = grupoPesquisa;
    }

    public LocalDate getDataInicioPesquisa() {
        return dataInicioPesquisa;
    }

    public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
        this.dataInicioPesquisa = dataInicioPesquisa;
    }

    public String getDuracaoProposta() {
        return duracaoProposta;
    }

    public void setDuracaoProposta(String duracaoProposta) {
        this.duracaoProposta = duracaoProposta;
    }

    public boolean isTermoAceiteProposta() {
        return termoAceiteProposta;
    }

    public void setTermoAceiteProposta(boolean termoAceiteProposta) {
        this.termoAceiteProposta = termoAceiteProposta;
    }

    public boolean isPatenteProposta() {
        return patenteProposta;
    }

    public void setPatenteProposta(boolean patenteProposta) {
        this.patenteProposta = patenteProposta;
    }

    public boolean isInovacaoProposta() {
        return inovacaoProposta;
    }

    public void setInovacaoProposta(boolean inovacaoProposta) {
        this.inovacaoProposta = inovacaoProposta;
    }

    public boolean isEticaProposta() {
        return eticaProposta;
    }

    public void setEticaProposta(boolean eticaProposta) {
        this.eticaProposta = eticaProposta;
    }

    public String getResumoProposta() {
        return resumoProposta;
    }

    public void setResumoProposta(String resumoProposta) {
        this.resumoProposta = resumoProposta;
    }

    public String getPalavraChaveProposta() {
        return palavraChaveProposta;
    }

    public void setPalavraChaveProposta(String palavraChaveProposta) {
        this.palavraChaveProposta = palavraChaveProposta;
    }

    public String getSinteseProposta() {
        return sinteseProposta;
    }

    public void setSinteseProposta(String sinteseProposta) {
        this.sinteseProposta = sinteseProposta;
    }

    public String getExpCordProposta() {
        return expCordProposta;
    }

    public void setExpCordProposta(String expCordProposta) {
        this.expCordProposta = expCordProposta;
    }

    public String getObjetivoGeralProposta() {
        return objetivoGeralProposta;
    }

    public void setObjetivoGeralProposta(String objetivoGeralProposta) {
        this.objetivoGeralProposta = objetivoGeralProposta;
    }

    public String getObjetivoEspProposta() {
        return objetivoEspProposta;
    }

    public void setObjetivoEspProposta(String objetivoEspProposta) {
        this.objetivoEspProposta = objetivoEspProposta;
    }

    public String getMetodologiaProposta() {
        return metodologiaProposta;
    }

    public void setMetodologiaProposta(String metodologiaProposta) {
        this.metodologiaProposta = metodologiaProposta;
    }

    public String getMetodosMatProposta() {
        return metodosMatProposta;
    }

    public void setMetodosMatProposta(String metodosMatProposta) {
        this.metodosMatProposta = metodosMatProposta;
    }

    public String getResultsProposta() {
        return resultsProposta;
    }

    public void setResultsProposta(String resultsProposta) {
        this.resultsProposta = resultsProposta;
    }

    public String getImpactosProposta() {
        return impactosProposta;
    }

    public void setImpactosProposta(String impactosProposta) {
        this.impactosProposta = impactosProposta;
    }

    public String getImpactosCienProposta() {
        return impactosCienProposta;
    }

    public void setImpactosCienProposta(String impactosCienProposta) {
        this.impactosCienProposta = impactosCienProposta;
    }

    public String getImpactosTechProposta() {
        return impactosTechProposta;
    }

    public void setImpactosTechProposta(String impactosTechProposta) {
        this.impactosTechProposta = impactosTechProposta;
    }

    public String getImpactosSocialProposta() {
        return impactosSocialProposta;
    }

    public void setImpactosSocialProposta(String impactosSocialProposta) {
        this.impactosSocialProposta = impactosSocialProposta;
    }

    public String getImpactosAmbientalProposta() {
        return impactosAmbientalProposta;
    }

    public void setImpactosAmbientalProposta(String impactosAmbientalProposta) {
        this.impactosAmbientalProposta = impactosAmbientalProposta;
    }

    public String getRefBlibProposta() {
        return refBlibProposta;
    }

    public void setRefBlibProposta(String refBlibProposta) {
        this.refBlibProposta = refBlibProposta;
    }

    public String getEstadoArteProposta() {
        return estadoArteProposta;
    }

    public void setEstadoArteProposta(String estadoArteProposta) {
        this.estadoArteProposta = estadoArteProposta;
    }

    public String getJustCopInterProposta() {
        return justCopInterProposta;
    }

    public void setJustCopInterProposta(String justCopInterProposta) {
        this.justCopInterProposta = justCopInterProposta;
    }

    public String getQualiParceriasProposta() {
        return qualiParceriasProposta;
    }

    public void setQualiParceriasProposta(String qualiParceriasProposta) {
        this.qualiParceriasProposta = qualiParceriasProposta;
    }

    public String getObrasInstalProposta() {
        return obrasInstalProposta;
    }

    public void setObrasInstalProposta(String obrasInstalProposta) {
        this.obrasInstalProposta = obrasInstalProposta;
    }

    public String getEstadoProposta() {
        return estadoProposta;
    }

    public void setEstadoProposta(String estadoProposta) {
        this.estadoProposta = estadoProposta;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public FaixaFinanciamento getFaixaFinanciamento() {
        return faixaFinanciamento;
    }

    public void setFaixaFinanciamento(FaixaFinanciamento faixaFinanciamento) {
        this.faixaFinanciamento = faixaFinanciamento;
    }

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

    public OrcamentoProposta getOrcamentoProposta() {
        return orcamentoProposta;
    }

    public void setOrcamentoProposta(OrcamentoProposta orcamentoProposta) {
        this.orcamentoProposta = orcamentoProposta;
    }
    
}
