package fapacapi.controller.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fapacapi.controller.dto.EditalOrcamentoDto;
import fapacapi.model.Edital;

@Mapper(componentModel = "spring")
public interface EditalOrcamentoMapper {

    @Mapping(target = "moeda", source = "moeda.idMoeda")
    EditalOrcamentoDto toDto(Edital edital);

    @InheritInverseConfiguration
    Edital toEntity(EditalOrcamentoDto editalOrcamentoDto);
}