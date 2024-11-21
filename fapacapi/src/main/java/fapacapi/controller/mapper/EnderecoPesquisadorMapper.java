package fapacapi.controller.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import fapacapi.controller.dto.EnderecoPesquisadorDto;
import fapacapi.model.EnderecoPesquisador;

@Mapper(componentModel = "spring")
public interface EnderecoPesquisadorMapper {

    @Mapping(target = "idPesquisador", source = "pesquisador.idPesquisador")
    @Mapping(target = "idEnderecoPesquisador", source = "idEnderecoPesquisador")
    @Mapping(target = "tipoEnderecoPesquisador", source = "tipoEnderecoPesquisador")
    @Mapping(target = "telefoneEnderecoPesquisador", source = "telefoneEnderecoPesquisador")
    @Mapping(target = "cepEnderecoPesquisador", source = "cepEnderecoPesquisador")
    @Mapping(target = "logradouroEnderecoPesquisador", source = "logradouroEnderecoPesquisador")
    @Mapping(target = "numeroEnderecoPesquisador", source = "numeroEnderecoPesquisador")
    @Mapping(target = "bairroEnderecoPesquisador", source = "bairroEnderecoPesquisador")
    @Mapping(target = "estadoEnderecoPesquisador", source = "estadoEnderecoPesquisador")
    @Mapping(target = "paisEnderecoPesquisador", source = "paisEnderecoPesquisador")
    EnderecoPesquisadorDto toDto(EnderecoPesquisador enderecoPesquisador);

    @InheritConfiguration
    EnderecoPesquisador toEntity(EnderecoPesquisadorDto enderecoPesquisadorDto);
}
