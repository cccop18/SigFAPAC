package fapacapi.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UnidadeInstituicaoDto(
    Long idUnidadeInstituicao,
    @NotBlank String nomeUnidade,
    @Email String emailUnidade,
    @NotBlank String telefoneUnidade,
    @NotBlank Long idEnderecoUnidade,
    @NotBlank Long idInstituicao,
    @NotBlank String cepEnderecoUnidade,
    @NotBlank String logradouroEnderecoUnidade,
    @NotBlank String numeroEnderecoUnidade,
    @NotBlank String bairroEnderecoUnidade,
    @NotBlank String estadoEnderecoUnidade,
    @NotBlank String paisEnderecoUnidade



) {

}
