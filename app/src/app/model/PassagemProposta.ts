import { Moeda } from "./Moeda";
import { OrcamentoProposta } from "./OrcamentoProposta";

export type PassagemProposta = {
    idPassagemProposta: number;
    tipoPassagemProposta: string;
    origemPassagemProposta: string;
    destinoPassagemProposta: string;
    idaVoltaPassagemProposta: boolean;
    quantidadePassagemProposta: number;
    custoUnitarioPassagemProposta: number;
    dataPassagemProposta: string;
    orcamentoProposta: OrcamentoProposta;
    moeda: Moeda
}