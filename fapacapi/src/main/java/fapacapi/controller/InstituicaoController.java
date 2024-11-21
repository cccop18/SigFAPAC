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

import fapacapi.model.Instituicao;
import fapacapi.service.InstituicaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/instituicoes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Instituicoes",
    description = "Endpoints para gerenciar instituicoes"
)
public class InstituicaoController implements IController<Instituicao>{

    private final InstituicaoService servico;

    public InstituicaoController(InstituicaoService servico) {
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
    @Operation(
        summary = "Obeter instituicoes ou filtar por termo busca",
        description = "Obtém todas as instituicoes"
    )
    public ResponseEntity<Page<Instituicao>> get(
            @RequestParam(required = false) String termoBusca, 
            @RequestParam(required = false, defaultValue = "false") boolean unpaged, 
            @SortDefault.SortDefaults({
                @SortDefault(sort = "nomeInstituicao", direction = Sort.Direction.ASC)
            }) 
            @ParameterObject Pageable page) {
        if (unpaged){
            page = Pageable.unpaged();
        }
        Page<Instituicao> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
    
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Instituicao encontrada"),
        @ApiResponse(
            responseCode = "404",
            description = "Instituição não encontrada",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obeter uma instituicao por ID",
        description = "Obtém a instituição com o ID infromado"
    )
    public ResponseEntity<Instituicao> get(@PathVariable("id") Long id) {
        Instituicao registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar instituição",
        description = "Cadastra uma nova instituição"
    )
    public ResponseEntity<Instituicao> insert(@RequestBody Instituicao objeto) {
        Instituicao registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar instituição",
        description = "Atualiza uma instituição existente"
    )
    public ResponseEntity<Instituicao> update(@RequestBody Instituicao objeto) {
        Instituicao registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar instituição",
        description = "Deleta uma instituição com o Id informado"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
}