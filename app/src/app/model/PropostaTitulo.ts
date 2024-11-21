import { PropostaConhecimento } from "./PropostaConhecimento";

export type PropostaTitulo = {
  idProposta: number | null;
  pesquisador: number | null;
  idEdital: number;
  tituloProposta: string | null;
  grupoPesquisa: string | null;

  dataInicioPesquisa: string | null;
  duracaoProposta: string | null;
  termoAceiteProposta: boolean;
  patenteProposta: boolean;
  inovacaoProposta: boolean;
  eticaProposta: boolean;

  idVinculoLoProposta: number | null;
  idInstituicao: number | null;
  idUnidade: number | null;

  propostaConhecimento: PropostaConhecimento[];
  };
  