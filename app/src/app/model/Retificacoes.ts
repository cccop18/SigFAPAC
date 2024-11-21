import { Edital } from "./Edital";

export type Retificacoes = {
    idRetificacoes: number;
    nomeRetificacoes: string;
    fileRetificacoes: string;
    dataRetificacoes: string;
    ativoRetificacoes: boolean;
    edital: Edital;
  };
  