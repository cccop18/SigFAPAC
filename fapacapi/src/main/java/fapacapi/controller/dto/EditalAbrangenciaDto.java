package fapacapi.controller.dto;

import fapacapi.model.Estado;

import java.util.List;

/**
 * DTO para representar a resposta ao criar ou atualizar uma nova Abrangência.
 * Contém o ID da Abrangência, um Edital e uma lista de Estados.
 */
public record EditalAbrangenciaDto(
    Long idAbrangencia,       // ID da Abrangência
    Long edital,              // ID do Edital
    List<Estado> estados      // Lista de objetos Estado associados à Abrangência
) {}
