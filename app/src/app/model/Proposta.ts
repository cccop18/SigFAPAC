import { Edital } from "./Edital";
import { FaixaFinanciamento } from "./FaixaFinanciamento";
import { OrcamentoProposta } from "./OrcamentoProposta";
import { Pesquisador } from "./Pesquisador";

export type Proposta = {
    idProposta: number;
    tituloProposta: string;
    grupoPesquisa: string;
    dataInicioPesquisa: string;
    duracaoProposta: number;
    termoAceiteProposta: boolean;
    patenteProposta: boolean;
    inovacaoProposta: boolean;
    eticaProposta: boolean;
    resumoProposta: string;
    palavraChaveProposta: string;
    sinteseProposta: string;
    expCordProposta: string;
    objetivoGeralProposta: string;
    objetivoEspProposta: string;
    metodologiaProposta: string;
    metodosMatProposta: string;
    resultsProposta: string;
    impactosProposta: string;
    impactosCienProposta: string;
    impactosTechProposta: string;
    // LONGBLOB
    impactosSocialProposta: string;
    impactosAmbientalProposta: string;
    refBlibProposta: string;
    estadoArteProposta: string;
    justCopInterProposta: string;
    qualiParceriasProposta: string;
    obrasInstalProposta: string;
    // LONGBLOB
    estadoProposta: string;
    situacao: string;
    faixaFinanciamento: FaixaFinanciamento;
    edital: Edital;
    pesquisador: Pesquisador;
    orcamentoProposta: OrcamentoProposta;
}