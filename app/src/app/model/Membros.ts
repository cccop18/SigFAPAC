import { Funcao } from "./Funcao";
import { Pesquisador } from "./Pesquisador";
import { Proposta } from "./Proposta";

export type Membros = {
    idMembros: number;
    proposta: Proposta;
    funcao: Funcao;
    pesquisador: Pesquisador;
}