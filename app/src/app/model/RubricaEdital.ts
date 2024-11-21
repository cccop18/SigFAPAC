import { Edital } from "./Edital";
import { Rubrica } from "./Rubrica";

export type RubricaEdital = {
    idRubricaEdital: number;
    // limiteDataRubricaEdital: boolean;
    porcentagemRubricaEdital: number;
    justificativaRubricaEdital: boolean;
    edital: Edital;
    rubrica: Rubrica;
}