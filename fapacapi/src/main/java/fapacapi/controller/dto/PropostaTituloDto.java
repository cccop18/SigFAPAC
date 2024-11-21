package fapacapi.controller.dto;

import jakarta.validation.constraints.NotNull;

public record PropostaTituloDto(
    Long idProposta,
    Long pesquisador,
    @NotNull Long idEdital,
    String tituloProposta,
    String grupoPesquisa,

    String dataInicioPesquisa,
    String duracaoProposta,
    boolean termoAceiteProposta,
    boolean patenteProposta,
    boolean inovacaoProposta,
    boolean eticaProposta,

    Long idVinculoLoProposta,
    Long idInstituicao,
    Long idUnidade
) {}
