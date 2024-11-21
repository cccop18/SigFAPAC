package fapacapi.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
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

import fapacapi.model.AtividadeProposta;
import fapacapi.service.AtividadePropostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/AtividadePropostas", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "AtividadeProposta",
    description = "Endpoints para gerenciar AtividadePropostas"
)
public class AtividadePropostaController implements IController<AtividadeProposta>{

    private final AtividadePropostaService servico;

    public AtividadePropostaController(AtividadePropostaService servico){
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
    @Operation(
        summary = "Obeter AtividadePropostas ou filtar por termo busca",
        description = "Obtém todas as AtividadePropostas"
    )
    public ResponseEntity<Page<AtividadeProposta>> get(
            @RequestParam(required = false) String termoBusca, 
            @RequestParam(required = false, defaultValue = "false") boolean unpaged, 
            @SortDefault.SortDefaults({
                @SortDefault(sort = "nomeInstituicao", direction = Sort.Direction.ASC)
            }) 
            @ParameterObject Pageable page) {
        if (unpaged){
            page = Pageable.unpaged();
        }
        Page<AtividadeProposta> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "AtividadeProposta encontrada"),
        @ApiResponse(
            responseCode = "404",
            description = "AtividadeProposta não encontrada",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obeter uma AtividadeProposta por ID",
        description = "Obtém a AtividadeProposta com o ID infromado"
    )
    public ResponseEntity<AtividadeProposta> get(@PathVariable("id") Long id) {
        AtividadeProposta registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar AtividadeProposta",
        description = "Cadastra uma nova AtividadeProposta"
    )
    public ResponseEntity<AtividadeProposta> insert(@RequestBody AtividadeProposta objeto) {
        AtividadeProposta registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar AtividadeProposta",
        description = "Atualiza uma AtividadePropostas existente"
    )
    public ResponseEntity<AtividadeProposta> update(@RequestBody AtividadeProposta objeto) {
        AtividadeProposta registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar AtividadeProposta",
        description = "Deleta uma AtividadeProposta com o Id informado"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
}
