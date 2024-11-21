import { Moeda } from "./Moeda";
import { OrcamentoProposta } from "./OrcamentoProposta";

export type MaterialPermProposta = {
    idMaterialPermProposta: number;
    especificacaoMaterialPermProposta: string;
    tipoMaterialPermProposta: string;
    quantidadeMaterialPermProposta: number;
    custoMaterialPermProposta: number;
    dataMaterialPermProposta: string;
    orcamentoProposta: OrcamentoProposta;
    moeda: Moeda;
  }
  
  