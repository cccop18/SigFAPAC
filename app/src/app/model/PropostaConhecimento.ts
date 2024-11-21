import { AreaConhecimento } from "./AreaConhecimento";
import { PrimeiraSubArea } from "./PrimeiraSubArea";
import { Proposta } from "./Proposta";
import { SegundaSubArea } from "./SegundaSubArea";
import { TerceiraSubArea } from "./TerceiraSubArea";

export type PropostaConhecimento = {
    idPropostaConhecimento: number;
    proposta: number;
    areaConhecimento: number;
    primeiraSubArea: number;
    segundaSubArea: number ;
    terceiraSubArea: number;
}