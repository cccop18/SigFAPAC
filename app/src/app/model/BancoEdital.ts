import { Banco } from "./Banco";
import { Edital } from "./Edital";

export type BancoEdital = {
    idBancoEdital: number;
    banco: Banco;
    edital: Edital;
  }