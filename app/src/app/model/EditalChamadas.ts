export type EditalChamadas = {
    idEdital: number;
    dataAberturaEdital: string;  // ISO string para LocalDate
    horaAberturaEdital: string;
    dataFechamentoEdital: string;
    horaFechamentoEdital: string;
    dataEnquadramentoEdital: string;
    dataRecursoEdital: string;
    dataResultadoEdital: string;
    dataRecursoFinalEdital?: string;  // Opcional
    dataBolsaEdital?: string;         // Opcional
    informacaoChamadasEdital?: string; // Opcional
  };
  