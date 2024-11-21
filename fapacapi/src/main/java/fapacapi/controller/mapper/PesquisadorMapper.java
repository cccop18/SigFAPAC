package fapacapi.controller.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fapacapi.controller.dto.PesquisadorDto;
import fapacapi.model.Pesquisador;

@Mapper(componentModel = "spring")
public interface PesquisadorMapper {

    @Mapping(target = "idTipoPesquisador", source = "tipoPesquisador.idTipoPesquisador")
    PesquisadorDto tDto(Pesquisador pesquisador);

    @InheritConfiguration
    @Mapping(target = "senhaPesquisador", ignore = true)
    Pesquisador toEntity(PesquisadorDto pesquisador);
}
