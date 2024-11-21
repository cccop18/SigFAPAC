package fapacapi.controller.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fapacapi.controller.dto.PropostaConhecimentoDto;
import fapacapi.model.PropostaConhecimento;

@Mapper(componentModel = "spring")
public interface PropostaConhecimentoMapper {

    @Mapping(target = "proposta", source = "proposta.idProposta")
    @Mapping(target = "areaConhecimento", source = "areaConhecimento.idAreaConhecimento")
    @Mapping(target = "primeiraSubArea", source = "primeiraSubArea.id")
    @Mapping(target = "segundaSubArea", source = "segundaSubArea.idSegundaSubArea")
    @Mapping(target = "terceiraSubArea", source = "terceiraSubArea.id")
    PropostaConhecimentoDto tDto(PropostaConhecimento proposta);

    @InheritInverseConfiguration
    PropostaConhecimento toEntity(PropostaConhecimentoDto propostaConhecimentoDto);

}
