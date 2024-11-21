package fapacapi.controller.mapper;

import fapacapi.controller.dto.EditalAbrangenciaDto;
import fapacapi.model.Abrangencia;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EditalAbrangenciaMapper {

    // Converte Abrangencia para EditalAbrangenciaDto, mapeando o ID do Edital e a lista de Estado
    @Mapping(target = "edital", source = "edital.idEdital")  // Mapeia apenas o ID do Edital
    @Mapping(target = "estados", source = "estados")         // Mapeia a lista de Estado diretamente
    EditalAbrangenciaDto toDto(Abrangencia abrangencia);

    // Converte EditalAbrangenciaDto para Abrangencia, ignorando a lista de estados inicialmente
    @Mapping(target = "edital.idEdital", source = "edital")  // Mapeia apenas o ID do Edital
    @Mapping(target = "estados", source = "estados")         // Mapeia a lista de Estado diretamente
    Abrangencia toEntity(EditalAbrangenciaDto dto);
}
