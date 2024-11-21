import { OrcamentoProposta } from "./OrcamentoProposta";

export type PessoalProposta = {
    idPessoalProposta: number;
    funcaoPessoalProposta: string;
    formacaoPessoalProposta: string;
    perfilPessoalProposta: string;
    periodoPessoalProposta: number;
    inicioPessoalPropostacol: string;
    horaSemanaPessoalProposta: number;
    custoHoraPessoalProposta: number;
    custoTotalPessoalPropostacol: number;
    dataPessoalProposta: string;
    orcamentoProposta: OrcamentoProposta;
  };
  