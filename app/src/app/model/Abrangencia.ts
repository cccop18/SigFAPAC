import { Estado } from "./Estado";

export type Abrangencia = {
    idAbrangencia: number;
    edital: number | null;
    estados: Estado[];        
  };