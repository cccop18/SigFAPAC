import { Moeda } from "./Moeda";
import { OrcamentoProposta } from "./OrcamentoProposta";

export type HospedagemAlimentacaoProposta = {
    idHospedagemAlimentacaoProposta: number;
    paisHospedagemAlimentacaoProposta: String;
    estadoHospedagemAlimentacaoProposta:String;
    hospedagemAlimentacaoProposta:String;
    municipioHospedagemAlimentacaoProposta:String;
    provinciaHospedagemAlimentacaoProposta: String;
    especificacaoHospedagemAlimentacaoProposta:String;
    quantidadeHospedagemAlimentacaoProposta:number;
    custoHospedagemAlimentacaoProposta: number;
    dataHospedagemAlimentacaoProposta:String;
    orcamentoproposta:OrcamentoProposta;
    moeda:Moeda
}