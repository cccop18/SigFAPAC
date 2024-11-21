import { Instituicao } from "./Instituicao";
import { Proposta } from "./Proposta";
import { UnidadeInstituicao } from "./UnidadeInstituicao";

export type VinculoProposta = {
    idVinculoLoProposta: number;
    proposta: Proposta;
    instituicao: Instituicao;
    unidadeInstituicao: UnidadeInstituicao;
  };
  
  