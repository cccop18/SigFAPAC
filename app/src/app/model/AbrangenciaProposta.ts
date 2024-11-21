import { Estado } from "./Estado";
import { Proposta } from "./Proposta";

export type AbrangenciaProposta = {
    idAbrangenciaProposta: number;
    municipioAbrangenciaProposta: string;
    proposta: Proposta;
    estado: Estado;
}