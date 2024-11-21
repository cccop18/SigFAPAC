import { Moeda } from "./Moeda";
import { OrcamentoProposta } from "./OrcamentoProposta";

export type ServicoProposta = {
    idServicoProposta: number;
    tipoServicoProposta: string;
    especificacaoServicoProposta: string;
    custoServicoProposta: string;
    dataServicoProposta: string;
    orcamentoProposta: OrcamentoProposta;
    moeda: Moeda;
  }
  