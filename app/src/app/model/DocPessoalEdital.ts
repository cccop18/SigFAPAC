import { DocPessoal } from "./DocPessoal";
import { Edital } from "./Edital";

export type DocPessoalEdital = {
    idDocPessoalEdital: number;
    edital: Edital;
    pessoal: DocPessoal;
}