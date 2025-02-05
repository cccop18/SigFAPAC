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

import fapacapi.model.Funcao;
import fapacapi.service.FuncaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/funcao", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Função",
    description = "Endpoints para gerenciar funções"
)
public class FuncaoController implements IController<Funcao>{

    private final FuncaoService servico;

    public FuncaoController(FuncaoService servico) {
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
    @Operation(
        summary = "Obeter função ou filtar por termo busca",
        description = "Obtém todas as funções"
    )
    public ResponseEntity<Page<Funcao>> get(
            @RequestParam(required = false) String termoBusca, 
            @RequestParam(required = false, defaultValue = "false") boolean unpaged, 
            @SortDefault.SortDefaults({
                @SortDefault(sort = "nomeFuncao", direction = Sort.Direction.ASC)
            }) 
            @ParameterObject Pageable page) {
        if (unpaged){
            page = Pageable.unpaged();
        }
        Page<Funcao> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
    
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Função encontrada"),
        @ApiResponse(
            responseCode = "404",
            description = "Função não encontrada",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obeter uma função por ID",
        description = "Obtém a função com o ID infromado"
    )
    public ResponseEntity<Funcao> get(@PathVariable("id") Long id) {
        Funcao registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar funções",
        description = "Cadastra uma nova função"
    )
    public ResponseEntity<Funcao> insert(@RequestBody Funcao objeto) {
        Funcao registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar função",
        description = "Atualiza uma função existente"
    )
    public ResponseEntity<Funcao> update(@RequestBody Funcao objeto) {
        Funcao registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar função",
        description = "Deleta uma função com o Id informado"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
}