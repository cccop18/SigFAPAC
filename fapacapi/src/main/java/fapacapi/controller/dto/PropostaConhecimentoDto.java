package fapacapi.controller.dto;

import jakarta.validation.constraints.NotNull;

public record PropostaConhecimentoDto(

    Long idPropostaConhecimento,
    @NotNull Long proposta,
    @NotNull Long areaConhecimento,
    @NotNull Long primeiraSubArea,
    Long segundaSubArea,
    Long terceiraSubArea
) {}
