package fapacapi.controller.mapper;

import fapacapi.controller.dto.AbrangenciaPropostaDto;
import fapacapi.model.AbrangenciaProposta;

import org.mapstruct.*;
@Mapper(componentModel = "spring")
public interface AbrangenciaPropostaMapper {

    @Mapping(target = "proposta", source = "proposta.idProposta")
    AbrangenciaPropostaDto toDto(AbrangenciaProposta abrangenciaProposta);

    // Mapeamento de DTO para entidade
    @Mapping(target = "proposta.idProposta", source = "dto.proposta")
    AbrangenciaProposta toEntity(AbrangenciaPropostaDto dto);
}

