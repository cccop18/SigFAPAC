import { TipoPesquisador } from "./TipoPesquisador";

export type Pesquisador = {
    idPesquisador: number;
    nomeCompletoPesquisador: string;
    papelPesquisador: string;
    fotoPesquisador?: Uint8Array; 
    emailPesquisador: string;
    sexoPesquisador: string;
    dataNascimentoPesquisador: string; 
    corRaca: string;
    nomeMaePesquisador: string;
    nomePaiPesquisador: string;
    curriculoLattesPesquisador: string;
    nivelAcademicoPesquisador: string;
    cpfPesquisador: string;
    //cpfFilePesquisador: Uint8Array; 
    senhaPesquisador: string;
    rgPesquisador: string;
    rgFilePesquisador: Uint8Array;
    orgaoEmissorPesquisador: string;
    ufPesquisador: string;
    dataEmissaoPesquisador: string;
    passaportePesquisador: string;
    passaporteFilePesquisador: Uint8Array;
    telefonePesquisador: string;
    tipoPesquisador: TipoPesquisador;
}
