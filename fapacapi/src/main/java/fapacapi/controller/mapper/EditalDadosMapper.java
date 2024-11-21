package fapacapi.controller.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fapacapi.controller.dto.EditalDadosDto;
import fapacapi.model.Edital;

@Mapper(componentModel = "spring")
public interface EditalDadosMapper {
    @Mapping(target = "programa", source = "programa.idPrograma")
    EditalDadosDto toDto(Edital edital);

    @InheritInverseConfiguration
    Edital toEntity(EditalDadosDto editalDadosDto);
}
