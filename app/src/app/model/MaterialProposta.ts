import { Moeda } from "./Moeda";
import { OrcamentoProposta } from "./OrcamentoProposta";

export type MaterialProposta = {
    idMaterialProposta: number;
    especificacaoMaterialProposta: string;
    quantidadeMaterialProposta: number;
    medidaMaterialProposta: string;
    custoUniMaterialProposta: number;
    dataMaterialProposta: string;
    orcamentoProposta: OrcamentoProposta;
    moeda: Moeda;
}