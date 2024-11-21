export type EditalOrcamento = {
    idEdital: number;
    cordenadorConfigOrcamentoEdital: boolean | null;
    moeda: number | null;
    limiteAnualPrimeiroAno: string;
    limiteAnualSegundoAno: string;
    limiteAnualTerceiroAno: string;
    limiteAnualQuartoAno: string;
    limiteAnualQuintoAno: string;
    rubricasSelecionadas: { idRubrica: number }[];
  }
  