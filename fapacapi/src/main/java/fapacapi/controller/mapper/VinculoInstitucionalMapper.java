package fapacapi.controller.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fapacapi.controller.dto.VinculoInstitucionalDto;
import fapacapi.model.VinculoInstitucional;

@Mapper(componentModel = "spring")
public interface VinculoInstitucionalMapper {

    @Mapping(target = "idPesquisador", source = "pesquisador.idPesquisador")
    @Mapping(target = "idInstituicao", source = "instituicao.idInstituicao")
    @Mapping(target = "idUnidadeInstituicao", source = "unidadeInstituicao.idUnidadeInstituicao")
    VinculoInstitucionalDto toDto(VinculoInstitucional vinculoInstitucional);

    @InheritInverseConfiguration
    VinculoInstitucional toEntity(VinculoInstitucionalDto vinculoInstitucionalDto);
}
