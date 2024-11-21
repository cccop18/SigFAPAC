import { Edital } from "./Edital";

export type FaixaFinanciamento = {
    idFaixaFinanciamento: number; 
    nomeFaixaFinanciamento: string;
    minFaixaFinanciamento: number;
    maxFaixaFinanciamento: number;
    observacaoFaixaFinanciamento: string;
    edital: Edital;
}