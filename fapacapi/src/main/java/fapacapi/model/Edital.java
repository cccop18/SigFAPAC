package fapacapi.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Edital implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idEdital;
    
    @Column
    private String tituloEdital;

    @Column
    private String fileEdital;

    @Column
    private LocalDateTime dataCriacaoEdital;

    @Column
    private String identificacaoEdital;

    @Column
    private String numeroAnoEdital;

    @Column
    private String nomeFormularioEdital;

    @Column
    private int codUnidadeAdmEdital;

    @Lob
    @Column(columnDefinition = "MEDIUMTEXT")
    private String textoChamadaEdital;

    @Column
    private String duracaoPropostaEdital;

    @Column
    private boolean multiplasPropostaEdital;

    @Column
    private boolean cordenadorParticipaEdital;

    @Column
    private boolean cargaHorariaEdital;

    @Column
    private boolean escolheDuracaoEdital;

    @Column
    private boolean obgOrientadorEdital;

    @Column
    private boolean cordenadorBolsaEdital;

    @Column
    private boolean editalEspecial;

    @Column
    private boolean proponenteBolsaEdital;

    @Column
    private boolean patenteEdital;

    @Column
    private boolean inovacaoTecEdital;

    @Column
    private boolean autoEticaEdital;

    @Column
    private boolean termoAceiteBooleanEdital;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String termoAceiteTextoEdital;

    @Column
    private LocalDate dataAberturaEdital;

    @Column
    private LocalTime horaAberturaEdital;

    @Column
    private LocalDate dataFechamentoEdital;

    @Column
    private LocalTime horaFechamentoEdital;

    @Column
    private LocalDate dataEnquadramentoEdital;

    @Column
    private LocalDate dataRecursoEdital;

    @Column
    private LocalDate dataResultadoEdital;

    @Column
    private LocalDate dataRecursoFinalEdital;

    @Column
    private LocalDate dataBolsaEdital;

    @Lob
    @Column(columnDefinition = "MEDIUMTEXT")
    private String informacaoChamadasEdital;

    @Column
    private String limiteAnualPrimeiroAno;

    @Column
    private String limiteAnualSegundoAno;

    @Column
    private String limiteAnualTerceiroAno;

    @Column
    private String limiteAnualQuartoAno;

    public String getLimiteAnualPrimeiroAno() {
        return limiteAnualPrimeiroAno;
    }

    public void setLimiteAnualPrimeiroAno(String limiteAnualPrimeiroAno) {
        this.limiteAnualPrimeiroAno = limiteAnualPrimeiroAno;
    }

    public String getLimiteAnualSegundoAno() {
        return limiteAnualSegundoAno;
    }

    public void setLimiteAnualSegundoAno(String limiteAnualSegundoAno) {
        this.limiteAnualSegundoAno = limiteAnualSegundoAno;
    }

    public String getLimiteAnualTerceiroAno() {
        return limiteAnualTerceiroAno;
    }

    public void setLimiteAnualTerceiroAno(String limiteAnualTerceiroAno) {
        this.limiteAnualTerceiroAno = limiteAnualTerceiroAno;
    }

    public String getLimiteAnualQuartoAno() {
        return limiteAnualQuartoAno;
    }

    public void setLimiteAnualQuartoAno(String limiteAnualQuartoAno) {
        this.limiteAnualQuartoAno = limiteAnualQuartoAno;
    }

    public String getLimiteAnualQuintoAno() {
        return limiteAnualQuintoAno;
    }

    public void setLimiteAnualQuintoAno(String limiteAnualQuintoAno) {
        this.limiteAnualQuintoAno = limiteAnualQuintoAno;
    }

    @Column
    private String limiteAnualQuintoAno;

    @Column
    private boolean cordenadorConfigOrcamentoEdital; // Orcamento

    @OneToOne
    @JoinColumn(name = "idMoeda", nullable = true)
    private Moeda moeda; // Orcamento

    @Column
    private boolean proponenteCurriculoEdital; // restricoes

    @Column
    private boolean membroCurriculoEdital; // restricoes

    @Column
    private boolean habilitaVinculoEmpregaEdital; // restricoes

    @Column
    private boolean proponenteVinculoEmpregaEdital; // restricoes

    @Column
    private boolean habilitaVinculoInstituicaoEdital;  // restricoes

    @Column
    private boolean proponenteVinculoInstituicaoEdital; // restricoes

    @Column
    private String proponenteNivelAcademicoEdital; // restricoes

    @Column(nullable = true)
    private boolean experienciaCordenadorEdital; //Plano

    @Column(nullable = true)
    private boolean objetivoGeralEdital; //Plano

    @Column(nullable = true)
    private boolean objetivoEspecificoEdital; //Plano

    @Column(nullable = true)
    private boolean metodologiaEdital; //Plano

    @Column(nullable = true)
    private boolean metodoMatEdital; //Plano

    @Column(nullable = true)
    private boolean resultadosEdital; //Plano

    @Column(nullable = true)
    private boolean impactoEsperadoEdital; //Plano

    @Column(nullable = true)
    private boolean impactoDiscriminadoEdital; //Plano

    @Column(nullable = true)
    private boolean riscoAtividadeEdital; //Plano

    @Column(nullable = true)
    private boolean referenciaBlibEdital; //Plano

    @Column(nullable = true)
    private boolean estadoArteEdital; //Plano

    @Column(nullable = true)
    private boolean justCopInterEdital; //Plano

    @Column(nullable = true)
    private boolean qualiParceriasEdital; //Plano

    @Column(nullable = true)
    private boolean obrasInstalEdital; //Plano

    @Column(nullable = true)
    private boolean prodBlibEdital; //Plano

    @Column(nullable = true)
    private boolean prodCulturaEdital; //Plano

    @Column(nullable = true)
    private boolean prodTecEdital; //Plano

    @Column(nullable = true)
    private boolean prodDifuEdital; //Plano

    @Column
    private String situacaoEdital;

    @ManyToOne
    @JoinColumn(name = "idPrograma", nullable = true)
    private Programa programa;

    public Long getIdEdital() {
        return idEdital;
    }

    public void setIdEdital(Long idEdital) {
        this.idEdital = idEdital;
    }

    public LocalDateTime getDataCriacaoEdital() {
        return dataCriacaoEdital;
    }

    public void setDataCriacaoEdital(LocalDateTime dataCriacaoEdital) {
        this.dataCriacaoEdital = dataCriacaoEdital;
    }

    public String getTituloEdital() {
        return tituloEdital;
    }

    public void setTituloEdital(String tituloEdital) {
        this.tituloEdital = tituloEdital;
    }

    public String getIdentificacaoEdital() {
        return identificacaoEdital;
    }

    public void setIdentificacaoEdital(String identificacaoEdital) {
        this.identificacaoEdital = identificacaoEdital;
    }

    public String getNumeroAnoEdital() {
        return numeroAnoEdital;
    }

    public void setNumeroAnoEdital(String numeroAnoEdital) {
        this.numeroAnoEdital = numeroAnoEdital;
    }

    public String getNomeFormularioEdital() {
        return nomeFormularioEdital;
    }

    public void setNomeFormularioEdital(String nomeFormularioEdital) {
        this.nomeFormularioEdital = nomeFormularioEdital;
    }

    public int getCodUnidadeAdmEdital() {
        return codUnidadeAdmEdital;
    }

    public void setCodUnidadeAdmEdital(int codUnidadeAdmEdital) {
        this.codUnidadeAdmEdital = codUnidadeAdmEdital;
    }

    public String getTextoChamadaEdital() {
        return textoChamadaEdital;
    }

    public void setTextoChamadaEdital(String textoChamadaEdital) {
        this.textoChamadaEdital = textoChamadaEdital;
    }

    public String getDuracaoPropostaEdital() {
        return duracaoPropostaEdital;
    }

    public void setDuracaoPropostaEdital(String duracaoPropostaEdital) {
        this.duracaoPropostaEdital = duracaoPropostaEdital;
    }

    public boolean isMultiplasPropostaEdital() {
        return multiplasPropostaEdital;
    }

    public void setMultiplasPropostaEdital(boolean multiplasPropostaEdital) {
        this.multiplasPropostaEdital = multiplasPropostaEdital;
    }

    public boolean isCordenadorParticipaEdital() {
        return cordenadorParticipaEdital;
    }

    public void setCordenadorParticipaEdital(boolean cordenadorParticipaEdital) {
        this.cordenadorParticipaEdital = cordenadorParticipaEdital;
    }

    public boolean isCargaHorariaEdital() {
        return cargaHorariaEdital;
    }

    public void setCargaHorariaEdital(boolean cargaHorariaEdital) {
        this.cargaHorariaEdital = cargaHorariaEdital;
    }

    public boolean isEscolheDuracaoEdital() {
        return escolheDuracaoEdital;
    }

    public void setEscolheDuracaoEdital(boolean escolheDuracaoEdital) {
        this.escolheDuracaoEdital = escolheDuracaoEdital;
    }

    public boolean isObgOrientadorEdital() {
        return obgOrientadorEdital;
    }

    public void setObgOrientadorEdital(boolean obgOrientadorEdital) {
        this.obgOrientadorEdital = obgOrientadorEdital;
    }

    public boolean isCordenadorBolsaEdital() {
        return cordenadorBolsaEdital;
    }

    public void setCordenadorBolsaEdital(boolean cordenadorBolsaEdital) {
        this.cordenadorBolsaEdital = cordenadorBolsaEdital;
    }

    public boolean isEditalEspecial() {
        return editalEspecial;
    }

    public void setEditalEspecial(boolean editalEspecial) {
        this.editalEspecial = editalEspecial;
    }

    public boolean isProponenteBolsaEdital() {
        return proponenteBolsaEdital;
    }

    public void setProponenteBolsaEdital(boolean proponenteBolsaEdital) {
        this.proponenteBolsaEdital = proponenteBolsaEdital;
    }

    public boolean isPatenteEdital() {
        return patenteEdital;
    }

    public void setPatenteEdital(boolean patenteEdital) {
        this.patenteEdital = patenteEdital;
    }

    public boolean isInovacaoTecEdital() {
        return inovacaoTecEdital;
    }

    public void setInovacaoTecEdital(boolean inovacaoTecEdital) {
        this.inovacaoTecEdital = inovacaoTecEdital;
    }

    public boolean isAutoEticaEdital() {
        return autoEticaEdital;
    }

    public void setAutoEticaEdital(boolean autoEticaEdital) {
        this.autoEticaEdital = autoEticaEdital;
    }

    public boolean isTermoAceiteBooleanEdital() {
        return termoAceiteBooleanEdital;
    }

    public void setTermoAceiteBooleanEdital(boolean termoAceiteBooleanEdital) {
        this.termoAceiteBooleanEdital = termoAceiteBooleanEdital;
    }

    public String getTermoAceiteTextoEdital() {
        return termoAceiteTextoEdital;
    }

    public void setTermoAceiteTextoEdital(String termoAceiteTextoEdital) {
        this.termoAceiteTextoEdital = termoAceiteTextoEdital;
    }

    public LocalDate getDataAberturaEdital() {
        return dataAberturaEdital;
    }

    public void setDataAberturaEdital(LocalDate dataAberturaEdital) {
        this.dataAberturaEdital = dataAberturaEdital;
    }

    public LocalTime getHoraAberturaEdital() {
        return horaAberturaEdital;
    }

    public void setHoraAberturaEdital(LocalTime horaAberturaEdital) {
        this.horaAberturaEdital = horaAberturaEdital;
    }

    public LocalDate getDataFechamentoEdital() {
        return dataFechamentoEdital;
    }

    public void setDataFechamentoEdital(LocalDate dataFechamentoEdital) {
        this.dataFechamentoEdital = dataFechamentoEdital;
    }

    public LocalTime getHoraFechamentoEdital() {
        return horaFechamentoEdital;
    }

    public void setHoraFechamentoEdital(LocalTime horaFechamentoEdital) {
        this.horaFechamentoEdital = horaFechamentoEdital;
    }

    public LocalDate getDataEnquadramentoEdital() {
        return dataEnquadramentoEdital;
    }

    public void setDataEnquadramentoEdital(LocalDate dataEnquadramentoEdital) {
        this.dataEnquadramentoEdital = dataEnquadramentoEdital;
    }

    public LocalDate getDataRecursoEdital() {
        return dataRecursoEdital;
    }

    public void setDataRecursoEdital(LocalDate dataRecursoEdital) {
        this.dataRecursoEdital = dataRecursoEdital;
    }

    public LocalDate getDataResultadoEdital() {
        return dataResultadoEdital;
    }

    public void setDataResultadoEdital(LocalDate dataResultadoEdital) {
        this.dataResultadoEdital = dataResultadoEdital;
    }

    public LocalDate getDataRecursoFinalEdital() {
        return dataRecursoFinalEdital;
    }

    public void setDataRecursoFinalEdital(LocalDate dataRecursoFinalEdital) {
        this.dataRecursoFinalEdital = dataRecursoFinalEdital;
    }

    public LocalDate getDataBolsaEdital() {
        return dataBolsaEdital;
    }

    public void setDataBolsaEdital(LocalDate dataBolsaEdital) {
        this.dataBolsaEdital = dataBolsaEdital;
    }

    public String getInformacaoChamadasEdital() {
        return informacaoChamadasEdital;
    }

    public void setInformacaoChamadasEdital(String informacaoChamadasEdital) {
        this.informacaoChamadasEdital = informacaoChamadasEdital;
    }

    public boolean isCordenadorConfigOrcamentoEdital() {
        return cordenadorConfigOrcamentoEdital;
    }

    public void setCordenadorConfigOrcamentoEdital(boolean cordenadorConfigOrcamentoEdital) {
        this.cordenadorConfigOrcamentoEdital = cordenadorConfigOrcamentoEdital;
    }

    public boolean isProponenteCurriculoEdital() {
        return proponenteCurriculoEdital;
    }

    public void setProponenteCurriculoEdital(boolean proponenteCurriculoEdital) {
        this.proponenteCurriculoEdital = proponenteCurriculoEdital;
    }

    public boolean isMembroCurriculoEdital() {
        return membroCurriculoEdital;
    }

    public void setMembroCurriculoEdital(boolean membroCurriculoEdital) {
        this.membroCurriculoEdital = membroCurriculoEdital;
    }

    public boolean isHabilitaVinculoEmpregaEdital() {
        return habilitaVinculoEmpregaEdital;
    }

    public void setHabilitaVinculoEmpregaEdital(boolean habilitaVinculoEmpregaEdital) {
        this.habilitaVinculoEmpregaEdital = habilitaVinculoEmpregaEdital;
    }

    public boolean isProponenteVinculoEmpregaEdital() {
        return proponenteVinculoEmpregaEdital;
    }

    public void setProponenteVinculoEmpregaEdital(boolean proponenteVinculoEmpregaEdital) {
        this.proponenteVinculoEmpregaEdital = proponenteVinculoEmpregaEdital;
    }

    public boolean isHabilitaVinculoInstituicaoEdital() {
        return habilitaVinculoInstituicaoEdital;
    }

    public void setHabilitaVinculoInstituicaoEdital(boolean habilitaVinculoInstituicaoEdital) {
        this.habilitaVinculoInstituicaoEdital = habilitaVinculoInstituicaoEdital;
    }

    public boolean isProponenteVinculoInstituicaoEdital() {
        return proponenteVinculoInstituicaoEdital;
    }

    public void setProponenteVinculoInstituicaoEdital(boolean proponenteVinculoInstituicaoEdital) {
        this.proponenteVinculoInstituicaoEdital = proponenteVinculoInstituicaoEdital;
    }

    public String getProponenteNivelAcademicoEdital() {
        return proponenteNivelAcademicoEdital;
    }

    public void setProponenteNivelAcademicoEdital(String proponenteNivelAcademicoEdital) {
        this.proponenteNivelAcademicoEdital = proponenteNivelAcademicoEdital;
    }

    public boolean isExperienciaCordenadorEdital() {
        return experienciaCordenadorEdital;
    }

    public void setExperienciaCordenadorEdital(boolean experienciaCordenadorEdital) {
        this.experienciaCordenadorEdital = experienciaCordenadorEdital;
    }

    public boolean isObjetivoGeralEdital() {
        return objetivoGeralEdital;
    }

    public void setObjetivoGeralEdital(boolean objetivoGeralEdital) {
        this.objetivoGeralEdital = objetivoGeralEdital;
    }

    public boolean isObjetivoEspecificoEdital() {
        return objetivoEspecificoEdital;
    }

    public void setObjetivoEspecificoEdital(boolean objetivoEspecificoEdital) {
        this.objetivoEspecificoEdital = objetivoEspecificoEdital;
    }

    public boolean isMetodologiaEdital() {
        return metodologiaEdital;
    }

    public void setMetodologiaEdital(boolean metodologiaEdital) {
        this.metodologiaEdital = metodologiaEdital;
    }

    public boolean isMetodoMatEdital() {
        return metodoMatEdital;
    }

    public void setMetodoMatEdital(boolean metodoMatEdital) {
        this.metodoMatEdital = metodoMatEdital;
    }

    public boolean isResultadosEdital() {
        return resultadosEdital;
    }

    public void setResultadosEdital(boolean resultadosEdital) {
        this.resultadosEdital = resultadosEdital;
    }

    public boolean isImpactoEsperadoEdital() {
        return impactoEsperadoEdital;
    }

    public void setImpactoEsperadoEdital(boolean impactoEsperadoEdital) {
        this.impactoEsperadoEdital = impactoEsperadoEdital;
    }

    public boolean isImpactoDiscriminadoEdital() {
        return impactoDiscriminadoEdital;
    }

    public void setImpactoDiscriminadoEdital(boolean impactoDiscriminadoEdital) {
        this.impactoDiscriminadoEdital = impactoDiscriminadoEdital;
    }

    public boolean isRiscoAtividadeEdital() {
        return riscoAtividadeEdital;
    }

    public void setRiscoAtividadeEdital(boolean riscoAtividadeEdital) {
        this.riscoAtividadeEdital = riscoAtividadeEdital;
    }

    public boolean isReferenciaBlibEdital() {
        return referenciaBlibEdital;
    }

    public void setReferenciaBlibEdital(boolean referenciaBlibEdital) {
        this.referenciaBlibEdital = referenciaBlibEdital;
    }

    public boolean isEstadoArteEdital() {
        return estadoArteEdital;
    }

    public void setEstadoArteEdital(boolean estadoArteEdital) {
        this.estadoArteEdital = estadoArteEdital;
    }

    public boolean isJustCopInterEdital() {
        return justCopInterEdital;
    }

    public void setJustCopInterEdital(boolean justCopInterEdital) {
        this.justCopInterEdital = justCopInterEdital;
    }

    public boolean isQualiParceriasEdital() {
        return qualiParceriasEdital;
    }

    public void setQualiParceriasEdital(boolean qualiParceriasEdital) {
        this.qualiParceriasEdital = qualiParceriasEdital;
    }

    public boolean isObrasInstalEdital() {
        return obrasInstalEdital;
    }

    public void setObrasInstalEdital(boolean obrasInstalEdital) {
        this.obrasInstalEdital = obrasInstalEdital;
    }

    public boolean isProdBlibEdital() {
        return prodBlibEdital;
    }

    public void setProdBlibEdital(boolean prodBlibEdital) {
        this.prodBlibEdital = prodBlibEdital;
    }

    public boolean isProdCulturaEdital() {
        return prodCulturaEdital;
    }

    public void setProdCulturaEdital(boolean prodCulturaEdital) {
        this.prodCulturaEdital = prodCulturaEdital;
    }

    public boolean isProdTecEdital() {
        return prodTecEdital;
    }

    public void setProdTecEdital(boolean prodTecEdital) {
        this.prodTecEdital = prodTecEdital;
    }

    public boolean isProdDifuEdital() {
        return prodDifuEdital;
    }

    public void setProdDifuEdital(boolean prodDifuEdital) {
        this.prodDifuEdital = prodDifuEdital;
    }

    public String getSituacaoEdital() {
        return situacaoEdital;
    }

    public void setSituacaoEdital(String situacaoEdital) {
        this.situacaoEdital = situacaoEdital;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public String getFileEdital() {
        return fileEdital;
    }

    public void setFileEdital(String fileEdital) {
        this.fileEdital = fileEdital;
    }

    public Moeda getMoeda() {
        return moeda;
    }

    public void setMoeda(Moeda moeda) {
        this.moeda = moeda;
    }

}