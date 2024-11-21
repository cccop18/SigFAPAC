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

import fapacapi.model.Programa;
import fapacapi.service.ProgramaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping( value = "/programa", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag( 
    name = "Programa",
    description = "Endpoints para gerenciar os programas"
)
public class ProgramaController implements IController<Programa> {
   
    private final ProgramaService servico;

    public ProgramaController(ProgramaService servico){
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
     @Operation(
        summary = "Obtém todos os programas ou filtra por termo de busca",
        description = "Obtém uma lista paginada de todos os programas cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<Programa>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "nomePrograma", direction = Sort.Direction.ASC)
        })
        @ParameterObject Pageable page) {
        Page<Programa> registros = servico.get(termoBusca, page); 
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Programa encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "Programa não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obtém o programa por ID",
        description = "Obtém o programa com o ID informado"
    )
    public ResponseEntity<Programa> get(@PathVariable("id") Long id) {
        Programa registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar um programa",
        description = "Cadastra um novo programa"
    )
    public ResponseEntity<Programa> insert(@RequestBody Programa objeto) {
    Programa registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar um programa",
        description = "Atualiza um programa"
    )
    public ResponseEntity<Programa> update(@RequestBody Programa objeto) {
        Programa registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar programa",
        description = "Deleta o programa com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}