// atividade-proposta.model.ts

import { Membros } from "./Membros";
import { Proposta } from "./Proposta";



export type AtividadeProposta = {
  idAtividadeProposta: number;
  descricaoProposta: string;
  valorAtividdeProposta: number; // Representado como string para data
  mesInicioAtividadeProposta: string;
  duracaoAtividadeProposta?: string;
  proposta: Proposta;
  membros: Membros;
};
