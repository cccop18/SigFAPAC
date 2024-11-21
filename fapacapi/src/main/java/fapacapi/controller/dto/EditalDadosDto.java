package fapacapi.controller.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditalDadosDto(
    Long idEdital,
    @NotBlank String tituloEdital,
    @NotBlank String identificacaoEdital,
    String fileEdital,
    LocalDateTime dataCriacaoEdital,
    String numeroAnoEdital,
    @NotNull Long programa,
    @NotBlank String nomeFormularioEdital,
    int codUnidadeAdmEdital,
    String textoChamadaEdital,
    String duracaoPropostaEdital,
    boolean multiplasPropostaEdital,
    boolean cordenadorParticipaEdital,
    boolean cargaHorariaEdital,
    boolean escolheDuracaoEdital, 
    boolean obgOrientadorEdital,
    boolean cordenadorBolsaEdital,
    boolean editalEspecial,
    boolean proponenteBolsaEdital,
    boolean patenteEdital,
    boolean inovacaoTecEdital,
    boolean autoEticaEdital,
    boolean termoAceiteBooleanEdital,
    String termoAceiteTextoEdital
) {

}
