package fapacapi.controller;

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
import org.springframework.web.bind.annotation.RestController;

import fapacapi.controller.dto.EditalRestricoesDto;
import fapacapi.controller.mapper.EditalRestricoesMapper;
import fapacapi.model.Edital;
import fapacapi.service.EditalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/editalRestricoes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Restrições do Edital",
    description = "Endpoints para gerenciar as restrições dos editais"
)
public class EditalRestricoesController {
    
    private final EditalService servico;
    private final EditalRestricoesMapper mapper;

    public EditalRestricoesController(EditalService servico, EditalRestricoesMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @GetMapping("/")
    @Operation(
        summary = "Obter ou filtrar restrições por termo de busca",
        description = "Obtém uma lista paginada de todos os restrições cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<?> get(String termoBusca, boolean unpaged, Pageable page) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Restrição encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "Restrição não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obter restrição por ID",
        description = "Obtém o restrição com o ID informado"
    )
    public ResponseEntity<EditalRestricoesDto> getById(@PathVariable("id") Long id) {
        Edital registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        EditalRestricoesDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/")
    public ResponseEntity<EditalRestricoesDto> insert(@RequestBody @Valid EditalRestricoesDto objeto) {
        Edital objetoConvertido = mapper.toEntity(objeto);
        Edital registro = servico.save(objetoConvertido);
        EditalRestricoesDto dto = mapper.toDto(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/")
    @Operation(
        summary = "Atualizar restrição",
        description = "Atualiza os dados de uma restrição existente"
    )
    public ResponseEntity<EditalRestricoesDto> update(@RequestBody @Valid EditalRestricoesDto objeto) {
        Edital registroAtualizado = servico.updateRestricoes(objeto);

        // Converte a entidade atualizada para DTO e retorna a resposta
        EditalRestricoesDto dto = mapper.toDto(registroAtualizado);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Excluir restrição",
        description = "Exclui um restrição com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.deleteRestricoes(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}