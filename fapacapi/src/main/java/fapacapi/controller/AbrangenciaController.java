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

import fapacapi.controller.dto.EditalAbrangenciaDto;
import fapacapi.controller.mapper.EditalAbrangenciaMapper;
import fapacapi.model.Abrangencia;
import fapacapi.service.AbrangenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/abrangencias", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Abrangencia", description = "Endpoints para gerenciar abrangencia")

public class AbrangenciaController implements IController<Abrangencia> {

    private final AbrangenciaService servico;
    private final EditalAbrangenciaMapper mapper;

    public AbrangenciaController(AbrangenciaService servico, EditalAbrangenciaMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @Override
    @GetMapping("/")
    @Operation(summary = "Obter abrangência ou filtrar por termo de busca", description = "Obtém todas as abrangências")
    public ResponseEntity<Page<EditalAbrangenciaDto>> get(
            @RequestParam(required = false) String termoBusca,
            @RequestParam(required = false, defaultValue = "false") boolean unpaged,
            @ParameterObject Pageable page) {
        if (unpaged) {
            page = Pageable.unpaged();
        }
        // Buscar abrangências e convertê-las para DTOs usando o mapper
        Page<Abrangencia> registros = servico.get(termoBusca, page);
        Page<EditalAbrangenciaDto> dtoPage = registros.map(mapper::toDto);
        return ResponseEntity.ok(dtoPage);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abrangência encontrada"),
            @ApiResponse(responseCode = "404", description = "Abrangência não encontrada", content = @Content(examples = @ExampleObject("")))
    })
    @Operation(summary = "Obter uma abrangência por ID", description = "Obtém a abrangência com o ID informado")
    public ResponseEntity<EditalAbrangenciaDto> get(@PathVariable("id") Long id) {
        Abrangencia registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        // Converter a entidade para DTO usando o mapper
        EditalAbrangenciaDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/")
    @Operation(summary = "Cadastrar abrangência", description = "Cadastra uma nova abrangência")
    public ResponseEntity<EditalAbrangenciaDto> insert(@RequestBody @Valid EditalAbrangenciaDto dto) {
        // Chama o serviço para salvar a abrangência e retorna o DTO de resposta
        EditalAbrangenciaDto abrangenciaSalva = servico.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(abrangenciaSalva);
    }

    @PutMapping("/")
    @Operation(summary = "Atualizar abrangência", description = "Atualiza uma abrangência existente")
    public ResponseEntity<EditalAbrangenciaDto> update(@RequestBody @Valid EditalAbrangenciaDto dto) {
        // Chama o serviço para atualizar a abrangência e retorna o DTO de resposta
        EditalAbrangenciaDto abrangenciaAtualizada = servico.save(dto);
        return ResponseEntity.ok(abrangenciaAtualizada);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar abrangência", description = "Deleta uma abrangência com o Id informado")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    public ResponseEntity<?> insert(Abrangencia objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public ResponseEntity<?> update(Abrangencia objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}