import { Instituicao } from "./Instituicao";
import { Pesquisador } from "./Pesquisador";
import { UnidadeInstituicao } from "./UnidadeInstituicao";

export type VinculoInstitucional = {
    idVinculoInstitucional: number | null;
    vinculoInstitucional: string;
    vinculoEmpregaticio: boolean;
    tempoServico: string;
    regimeTrabalho: string;
    funcaoCargo: string;
    tempoDaFuncao: string;
    idPesquisador: number;
    idInstituicao: number;
    idUnidadeInstituicao: number;
}