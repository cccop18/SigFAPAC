import { Moeda } from "./Moeda";
import { OrcamentoProposta } from "./OrcamentoProposta";

export type BolsaProposta = {
    idBolsaProposta:number;
    modalidadeBolsaProposta:String;
    quantidadeBolsaProposta:number;
    duracaoBolsaProposta:String;
    bolsaMesBolsaProposta:number;
    areaBolsaProposta:String;
    orcamentoProposta:OrcamentoProposta;
    moeda:Moeda;

}