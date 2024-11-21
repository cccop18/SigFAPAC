import { Moeda } from "./Moeda";
import { OrcamentoProposta } from "./OrcamentoProposta";

export type DiariaProposta = {
    idDiariaProposta: number;
    tipoLocalidadeDiariaProposta: string;
    paisDiariaProposta: string;
    estadoDiariaProposta: string;
    municipioDiariaProposta?: string;
    provinciaDiariaProposta?: string;
    numeroDiariaProposta: number;
    custoUnitDiariaProposta: number;
    data?: string;
    justicativa?: string;
    orcamentoProposta: OrcamentoProposta;
    moeda: Moeda;
  };
