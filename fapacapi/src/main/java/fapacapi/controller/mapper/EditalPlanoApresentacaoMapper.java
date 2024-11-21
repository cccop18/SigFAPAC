package fapacapi.controller.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import fapacapi.controller.dto.EditalPlanoApresentacaoDto;
import fapacapi.model.Edital;

@Mapper(componentModel = "spring")
public interface EditalPlanoApresentacaoMapper {

    EditalPlanoApresentacaoDto toDto(Edital edital);

    @InheritInverseConfiguration
    Edital toEntity(EditalPlanoApresentacaoDto editalPlanoApresentacaoDto);
}
