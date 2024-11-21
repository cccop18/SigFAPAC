package fapacapi.controller.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fapacapi.controller.dto.PropostaTituloDto;
import fapacapi.model.Edital;
import fapacapi.model.Proposta;

@Mapper(componentModel = "spring")
public interface PropostaTituloMapper {

    @Mapping(target = "idEdital", source = "edital.idEdital")
    @Mapping(target = "pesquisador", source = "pesquisador.idPesquisador")
    
    PropostaTituloDto toDto(Proposta proposta);

    @InheritInverseConfiguration
    Proposta toEntity(PropostaTituloDto propostaTituloDto);
    
}
