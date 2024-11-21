package fapacapi.controller.dto;

import jakarta.validation.constraints.NotNull;

public record PesquisadorConhecimentoDto(
    Long idPesquisadorConhecimento,
    @NotNull Long idAreaConhecimento,
    @NotNull Long idPrimeiraSubArea,
    Long idSegundaSubArea,
    Long idTerceiraSubArea
) {

}
