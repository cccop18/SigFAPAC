package fapacapi.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VinculoInstitucionalDto(
    Long idVinculoInstitucional, // ID opcional
    @NotBlank String vinculoInstitucional,
    @NotNull Boolean vinculoEmpregaticio,
    @NotBlank String tempoServico,
    @NotBlank String regimeTrabalho,
    @NotBlank String funcaoCargo,
    @NotBlank String tempoDaFuncao,
    Long idPesquisador, // ID do pesquisador
    @NotNull Long idInstituicao, // ID da instituição
    @NotNull Long idUnidadeInstituicao // ID da unidade de instituição
) {

}
