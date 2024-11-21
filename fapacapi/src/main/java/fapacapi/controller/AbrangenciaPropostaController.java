package fapacapi.controller;


import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fapacapi.controller.dto.AbrangenciaPropostaDto;
import fapacapi.controller.mapper.AbrangenciaPropostaMapper;
import fapacapi.model.AbrangenciaProposta;
import fapacapi.service.AbrangenciaPropostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/abrangenciaProposta", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "AbrangenciaProposta", description = "Endpoints para gerenciar Abrangencia Proposta")

public class AbrangenciaPropostaController implements IController<AbrangenciaProposta>{

    private final AbrangenciaPropostaService servico;
    private final AbrangenciaPropostaMapper mapper;

    public AbrangenciaPropostaController(AbrangenciaPropostaService servico, AbrangenciaPropostaMapper mapper){
        this.servico = servico;
        this.mapper = mapper;
    }

    @Override
    @GetMapping("/")
    @Operation(summary = "Obter abrangência ou filtrar por termo de busca", description = "Obtém todas as abrangências")
    public ResponseEntity<Page<AbrangenciaPropostaDto>> get(
            @RequestParam(required = false) String termoBusca,
            @RequestParam(required = false, defaultValue = "false") boolean unpaged,
            @ParameterObject Pageable page) {
        if (unpaged) {
            page = Pageable.unpaged();
        }
        Page<AbrangenciaProposta> registros = servico.get(termoBusca, page);
        Page<AbrangenciaPropostaDto> dtoPage = registros.map(mapper::toDto);
        return ResponseEntity.ok(dtoPage);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abrangência encontrada"),
            @ApiResponse(responseCode = "404", description = "Abrangência não encontrada", content = @Content(examples = @ExampleObject("")))
    })
    @Operation(summary = "Obter uma abrangência por ID", description = "Obtém a abrangência com o ID informado")
    public ResponseEntity<AbrangenciaPropostaDto> get(@PathVariable("id") Long id) {
        AbrangenciaProposta registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        AbrangenciaPropostaDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/")
    @Operation(summary = "Cadastrar abrangência", description = "Cadastra uma nova abrangência")
    public ResponseEntity<AbrangenciaPropostaDto> insert(@RequestBody @Valid AbrangenciaPropostaDto dto) {
        AbrangenciaProposta abrangenciaProposta = mapper.toEntity(dto);  // Converte o DTO para entidade
        AbrangenciaProposta abrangenciaSalva = servico.save(abrangenciaProposta);  // Salva a entidade
        AbrangenciaPropostaDto abrangenciaSalvaDto = mapper.toDto(abrangenciaSalva);  // Converte de volta para DTO
        return ResponseEntity.status(HttpStatus.CREATED).body(abrangenciaSalvaDto);
    }

    @PutMapping("/")
    @Operation(summary = "Atualizar abrangência", description = "Atualiza uma abrangência existente")
    public ResponseEntity<AbrangenciaPropostaDto> update(@RequestBody @Valid AbrangenciaPropostaDto dto) {
        AbrangenciaProposta abrangenciaProposta = mapper.toEntity(dto);  // Converte o DTO para entidade
        AbrangenciaProposta abrangenciaAtualizada = servico.save(abrangenciaProposta);  // Atualiza a entidade
        AbrangenciaPropostaDto abrangenciaAtualizadaDto = mapper.toDto(abrangenciaAtualizada);  // Converte de volta para DTO
        return ResponseEntity.ok(abrangenciaAtualizadaDto);
    }


    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar abrangência", description = "Deleta uma abrangência com o Id informado")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    public ResponseEntity<?> insert(AbrangenciaProposta objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public ResponseEntity<?> update(AbrangenciaProposta objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    
} 
