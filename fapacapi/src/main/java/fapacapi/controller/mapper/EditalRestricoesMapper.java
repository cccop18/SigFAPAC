package fapacapi.controller.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import fapacapi.controller.dto.EditalRestricoesDto;
import fapacapi.model.Edital;

@Mapper(componentModel = "spring")
public interface EditalRestricoesMapper {

    EditalRestricoesDto toDto(Edital edital);

    @InheritInverseConfiguration
    Edital toEntity(EditalRestricoesDto editalRestricoesDto);
}
