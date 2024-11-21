package fapacapi.controller.dto;

public record EditalRestricoesDto (
    Long idEdital,
    boolean proponenteCurriculoEdital,
    boolean membroCurriculoEdital,
    boolean habilitaVinculoEmpregaEdital,
    boolean proponenteVinculoEmpregaEdital,
    boolean habilitaVinculoInstituicaoEdital,
    boolean proponenteVinculoInstituicaoEdital,
    String proponenteNivelAcademicoEdital
) {
    
}
