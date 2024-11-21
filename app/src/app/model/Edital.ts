import { Programa } from "./Programa";

export type Edital = {
    idEdital: number;
    tituloEdital: string;
    fileEdital: string;
    identificacaoEdital: string;
    numeroAnoEdital: string;
    dataCriacaoEdital: string; // LocalDate -> string (ISO format)
    nomeFormularioEdital: string;
    codUnidadeAdmEdital: number;
    textoChamadaEdital: string;
    duracaoPropostaEdital: string;
    multiplasPropostaEdital?: boolean;
    cordenadorParticipaEdital?: boolean;
    cargaHorariaEdital?: boolean;
    escolheDuracaoEdital?: boolean;
    obgOrientadorEdital?: boolean;
    cordenadorBolsaEdital?: boolean;
    editalEspecial?: boolean;
    proponenteBolsaEdital?: boolean;
    patenteEdital?: boolean;
    inovacaoTecEdital?: boolean;
    autoEticaEdital?: boolean;
    termoAceiteBooleanEdital?: boolean;
    termoAceiteTextoEdital?: string;
    dataAberturaEdital: string; // LocalDate -> string (ISO format)
    horaAberturaEdital: string; // LocalTime -> string (ISO format)
    dataFechamentoEdital: string;
    horaFechamentoEdital: string;
    dataEnquadramentoEdital: string;
    dataRecursoEdital: string;
    dataResultadoEdital: string;
    dataRecursoFinalEdital: string;
    dataBolsaEdital: string;
    informacaoChamadasEdital?: string;
    cordenadorConfigOrcamentoEdital?: boolean;
    tipoMoedaEdital?: string;
    proponenteCurriculoEdital?: boolean;
    membroCurriculoEdital?: boolean;
    habilitaVinculoEmpregaEdital?: boolean;
    proponenteVinculoEmpregaEdital?: boolean;
    habilitaVinculoInstituicaoEdital?: boolean;
    proponenteVinculoInstituicaoEdital?: boolean;
    proponenteNivelAcademicoEdital?: string;
    experienciaCordenadorEdital?: boolean;
    objetivoGeralEdital?: boolean;
    objetivoEspecificoEdital?: boolean;
    metodologiaEdital?: boolean;
    metodoMatEdital?: boolean;
    resultadosEdital?: boolean;
    impactoEsperadoEdital?: boolean;
    impactoDiscriminadoEdital?: boolean;
    riscoAtividadeEdital?: boolean;
    referenciaBlibEdital?: boolean;
    estadoArteEdital?: boolean;
    justCopInterEdital?: boolean;
    qualiParceriasEdital?: boolean;
    obrasInstalEdital?: boolean;
    prodBlibEdital?: boolean;
    prodCulturaEdital?: boolean;
    prodTecEdital?: boolean;
    prodDifuEdital?: boolean;
    situacaoEdital: string;
    programa: Programa; // Relacionamento com Programa
  }
  
  
  