import { Moeda } from "./Moeda";
import { OrcamentoProposta } from "./OrcamentoProposta";

export type EncargoProposta = {
    idEncargoProposta: number;
    especificacaoEncargoProposta: string;
    custoTotalProposta: number;
    orcamentoProposta: OrcamentoProposta;
    moeda: Moeda;
}