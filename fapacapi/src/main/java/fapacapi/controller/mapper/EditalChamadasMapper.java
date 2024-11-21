package fapacapi.controller.mapper;

import org.mapstruct.Mapper;

import fapacapi.controller.dto.EditalChamadasDto;
import fapacapi.model.Edital;

@Mapper(componentModel = "spring")
public interface EditalChamadasMapper {

    EditalChamadasDto toDto(Edital edital);

    Edital toEntity(EditalChamadasDto editalChamadasDto);

}
