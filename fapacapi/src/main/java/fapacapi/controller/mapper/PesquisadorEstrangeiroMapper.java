package fapacapi.controller.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fapacapi.controller.dto.PesquisadorEstrangeiroDto;
import fapacapi.model.Pesquisador;

@Mapper(componentModel = "spring")
public interface PesquisadorEstrangeiroMapper {

    @Mapping(target = "idTipoPesquisador", source="tipoPesquisador.idTipoPesquisador")
    PesquisadorEstrangeiroDto toDto(Pesquisador pesquisador);

    @InheritConfiguration
    @Mapping(target = "senhaPesquisador", ignore = true)
    Pesquisador toEntity(PesquisadorEstrangeiroDto pesquisador);
}
