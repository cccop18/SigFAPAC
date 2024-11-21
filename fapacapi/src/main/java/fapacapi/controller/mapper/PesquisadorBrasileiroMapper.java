package fapacapi.controller.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fapacapi.controller.dto.PesquisadorBrasileiroDto;
import fapacapi.model.Pesquisador;

@Mapper(componentModel = "spring")
public interface PesquisadorBrasileiroMapper {

     @Mapping(target = "idTipoPesquisador", source="tipoPesquisador.idTipoPesquisador")
    PesquisadorBrasileiroDto toDto(Pesquisador pesquisador);

    @InheritConfiguration
    @Mapping(target = "senhaPesquisador", ignore = true)
    Pesquisador toEntity(PesquisadorBrasileiroDto pesquisador);

}
