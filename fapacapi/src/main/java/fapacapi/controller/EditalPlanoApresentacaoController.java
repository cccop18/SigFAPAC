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

import fapacapi.controller.dto.EditalPlanoApresentacaoDto;
import fapacapi.controller.mapper.EditalPlanoApresentacaoMapper;
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
@RequestMapping(value = "/editalPlanoApresentacao", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Plano de Apresentação do Edital",
    description = "Endpoints para gerenciar os planos de apresentação dos editais"
)
public class EditalPlanoApresentacaoController {

    private final EditalService servico;
    private final EditalPlanoApresentacaoMapper mapper;

    public EditalPlanoApresentacaoController(EditalService servico, EditalPlanoApresentacaoMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @GetMapping("/")
    @Operation(
        summary = "Obter ou filtrar planos de apresentação por termo de busca",
        description = "Obtém uma lista paginada de todos os planos de apresentação cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<?> get(String termoBusca, boolean unpaged, Pageable page) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Plano de Apresentação encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "Plano de Apresentação não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obter plano de apresentação por ID",
        description = "Obtém o plano de apresentação com o ID informado"
    )
    public ResponseEntity<EditalPlanoApresentacaoDto> getById(@PathVariable("id") Long id) {
        Edital registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        EditalPlanoApresentacaoDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/")
    public ResponseEntity<EditalPlanoApresentacaoDto> insert(@RequestBody @Valid EditalPlanoApresentacaoDto objeto) {
        Edital objetoConvertido = mapper.toEntity(objeto);
        Edital registro = servico.save(objetoConvertido);
        EditalPlanoApresentacaoDto dto = mapper.toDto(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/")
    @Operation(
        summary = "Atualizar plano de apresentação",
        description = "Atualiza os dados de um plano de apresentação existente"
    )
    public ResponseEntity<EditalPlanoApresentacaoDto> update(@RequestBody @Valid EditalPlanoApresentacaoDto objeto) {
        Edital registroAtualizado = servico.updatePlanoApresentacao(objeto);

        // Converte a entidade atualizada para DTO e retorna a resposta
        EditalPlanoApresentacaoDto dto = mapper.toDto(registroAtualizado);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Excluir plano de apresentação",
        description = "Exclui um plano de apresentação com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.deletePlanoApresentacao(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
