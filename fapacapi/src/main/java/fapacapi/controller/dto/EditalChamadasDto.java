package fapacapi.controller.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditalChamadasDto(
    Long idEdital,
    @NotNull @FutureOrPresent LocalDate dataAberturaEdital,
    @NotBlank String horaAberturaEdital,
    @NotNull LocalDate dataFechamentoEdital,
    @NotBlank String horaFechamentoEdital,
    @NotNull LocalDate dataEnquadramentoEdital,
    @NotNull LocalDate dataRecursoEdital,
    @NotNull LocalDate dataResultadoEdital,
    LocalDate dataRecursoFinalEdital,
    LocalDate dataBolsaEdital,
    String informacaoChamadasEdital
) {

}
