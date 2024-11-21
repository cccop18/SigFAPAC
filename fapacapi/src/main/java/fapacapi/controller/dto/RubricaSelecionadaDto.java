package fapacapi.controller.dto;

public record RubricaSelecionadaDto(
    Long idRubrica,
    Integer porcentagemRubricaEdital  // Pode ser null
) {}
