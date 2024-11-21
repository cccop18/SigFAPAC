import { EnderecoUnidade } from "./EnderecoUnidade";
import { Instituicao } from "./Instituicao";

export type UnidadeInstituicao = {
    id: number;
    nome_unidade: string;
    email_unidade: string;
    telefone_unidade: string;
    enderecoUnidade: EnderecoUnidade;  
    instituicao: Instituicao; 
}