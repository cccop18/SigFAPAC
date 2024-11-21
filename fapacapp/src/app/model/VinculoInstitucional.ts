import { Instituicao } from "./Instituicao";
import { Pesquisador } from "./Pesquisador";

export type VinculoInstitucional = {
    idVinculoInstitucional: number;
    vinculoInstitucional: string;
    vinculoEmpregaticio: boolean;
    tempoServico: string;
    regimeTrabalho: string;
    funcaoCargo: string;
    tempoDaFuncao: string;
    pesquisadorId: Pesquisador;
    idInstituicao: Instituicao;
}