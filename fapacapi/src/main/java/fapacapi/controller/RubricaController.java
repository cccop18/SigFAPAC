package fapacapi.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.data.domain.Sort;
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

import fapacapi.model.Rubrica;
import fapacapi.service.RubricaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping(value = "/rubricas", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Rubricas",
    description = "Endipoints para gerenciar Rubricas"
)
public class RubricaController implements IController<Rubrica>{

    private final RubricaService servico;

    public RubricaController(RubricaService servico) {
        this.servico = servico;
    }

    @GetMapping("/")
    @Operation(
        summary = "Obtém todas as Rubricas ou filtra por termo busca",
        description = "Obtém uma lista paginada de todas as Rubricas que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<Rubrica>> get( 
        @RequestParam(required = false) String termoBusca, 
        @RequestParam(required = false) boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "nomeRubrica", direction = Sort.Direction.ASC)  // Altere aqui para o nome correto
        })
        @ParameterObject Pageable page) {
        Page<Rubrica> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
    }    

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rubricas"),
        @ApiResponse(
            responseCode = "404",
            description = "Rubrica não encontrada",
            content = @Content(examples = @ExampleObject(""))
        )
    })
    @Operation(
        summary = "Obtém uma Rubrica por ID",
        description = "Obtém a Rubrica com o ID informado"
    )
    public ResponseEntity<Rubrica> get(@PathVariable("id") Long id) {
        Rubrica registro = servico.get(id);
        if(registro == null){
            return ResponseEntity.status(HttpStatus.NOT_EXTENDED).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Escolher uma Rubrica",
        description = "Escolher uma diaria"
    )
    public ResponseEntity<Rubrica> insert(@RequestBody Rubrica objeto) {
        Rubrica registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar uma Rubrica",
        description = "Atualizar uma Rubrica já existente"
    )
    public ResponseEntity<Rubrica> update(@RequestBody Rubrica objeto) {
        Rubrica registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar uma Rubrica",
        description = "Deleta uma Rubrica com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
}
