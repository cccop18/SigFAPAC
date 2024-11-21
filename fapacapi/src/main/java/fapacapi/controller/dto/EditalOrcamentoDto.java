package fapacapi.controller.dto;

import java.util.List;

public record EditalOrcamentoDto (
    Long idEdital,
    boolean cordenadorConfigOrcamentoEdital,
    String limiteAnualPrimeiroAno,
    String limiteAnualSegundoAno,
    String limiteAnualTerceiroAno,
    String limiteAnualQuartoAno,
    String limiteAnualQuintoAno,
    Long moeda,
    List<RubricaSelecionadaDto> rubricasSelecionadas
){}
