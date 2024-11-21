package fapacapi.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoPesquisadorDto(
    Long idEnderecoPesquisador,
    @NotBlank String tipoEnderecoPesquisador,
    @NotBlank String telefoneEnderecoPesquisador,
    @NotBlank String cepEnderecoPesquisador,
    @NotBlank String logradouroEnderecoPesquisador,
    @NotBlank String numeroEnderecoPesquisador,
    @NotBlank String bairroEnderecoPesquisador,
    @NotBlank String estadoEnderecoPesquisador,
    @NotBlank String paisEnderecoPesquisador,
    @NotNull Long idPesquisador
) {

}
