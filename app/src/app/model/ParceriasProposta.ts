import { Moeda } from "./Moeda";
import { OrcamentoProposta } from "./OrcamentoProposta";

export type ParceriasProposta = {
    idParceriasProposta: number;
    entidadeParceriaProposta: string;
    tipoParceriaProposta: string;
    descricaoParceriaProposta: string;
    orcamentoProposta: OrcamentoProposta;
    moeda: Moeda;
  };