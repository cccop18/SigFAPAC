import { AreaConhecimento } from "./AreaConhecimento";
import { Pesquisador } from "./Pesquisador";
import { PrimeiraSubArea } from "./PrimeiraSubArea";
import { SegundaSubArea } from "./SegundaSubArea";
import { TerceiraSubArea } from "./TerceiraSubArea";

export interface PesquisadorConhecimento {
    idPesquisadorConhecimento: number;
    pesquisador: Pesquisador;  
    areaConhecimento: AreaConhecimento;  
    primeiraSubArea: PrimeiraSubArea;  
    segundaSubArea: SegundaSubArea | null;  
    terceiraSubArea: TerceiraSubArea| null;  
}
