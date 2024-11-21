package fapacapi.controller.dto;

import fapacapi.model.Estado;

import java.util.List;

public record AbrangenciaPropostaDto(
    Long idAbrangenciaProposta,
    Long proposta,
    List<Estado> estados      
) {}
