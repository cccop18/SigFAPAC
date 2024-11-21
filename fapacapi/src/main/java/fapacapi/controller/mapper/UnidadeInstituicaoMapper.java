package fapacapi.controller.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fapacapi.controller.dto.UnidadeInstituicaoDto;
import fapacapi.model.UnidadeInstituicao;

@Mapper(componentModel = "spring")
public interface UnidadeInstituicaoMapper {
    
    @Mapping(target = "idEnderecoUnidade", source = "enderecoUnidade.idEnderecoUnidade")
    @Mapping(target = "cepEnderecoUnidade", source = "enderecoUnidade.cepEnderecoUnidade")
    @Mapping(target = "logradouroEnderecoUnidade", source = "enderecoUnidade.logradouroEnderecoUnidade")
    @Mapping(target = "numeroEnderecoUnidade", source = "enderecoUnidade.numeroEnderecoUnidade")
    @Mapping(target = "bairroEnderecoUnidade", source = "enderecoUnidade.bairroEnderecoUnidade")
    @Mapping(target = "estadoEnderecoUnidade", source = "enderecoUnidade.estadoEnderecoUnidade")
    @Mapping(target = "paisEnderecoUnidade", source = "enderecoUnidade.paisEnderecoUnidade")
    @Mapping(target = "idInstituicao", source = "instituicao.idInstituicao")
    UnidadeInstituicaoDto toDto(UnidadeInstituicao unidadeInstituicao);

    @InheritInverseConfiguration
    UnidadeInstituicao toEntity(UnidadeInstituicaoDto unidadeInstituicaoDto);   
}
