import { Arquivo } from "./Arquivo";
import { Edital } from "./Edital";

export type ArquivoEdital = {
    idArquivoEdital: number;
    arquivo: Arquivo;
    edital: Edital;
}