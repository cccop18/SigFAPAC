import { Proposta } from "./Proposta";

export type ParecerProposta = {
    idMeidParecerPropostambros: number;
    tituloParecerProposta: string;
    descricaoParecerProposta: string;
    fileParecerProposta: string;
    proposta: Proposta;
}