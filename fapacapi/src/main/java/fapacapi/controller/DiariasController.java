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

import fapacapi.model.Diarias;
import fapacapi.service.DiariasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/diarias", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Diarias",
    description = "Endipoints para gerenciar as Diarias"
)
public class DiariasController implements IController<Diarias>{
    
    private final DiariasService servico;

    public DiariasController(DiariasService servico) {
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
    @Operation(
        summary = "Obtém todas as Diarias ou filtra por termo de busca",
        description = "Obtém uma lista paginada de todas as Diarias que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<Diarias>> get(
        @RequestParam(required = false) String termoBusca, 
        @RequestParam(required = false) boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "nivelAcademicoDiaria", direction = Sort.Direction.ASC)
        }) 
        @ParameterObject Pageable page) {
        Page<Diarias> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Diarias"),
        @ApiResponse(
            responseCode = "404",
            description = "Diaria não encontrada",
            content = @Content(examples = @ExampleObject(""))
        )
    })
    @Operation(
        summary = "Obtém uma Diaria por ID",
        description = "Obtém a Diaria com o ID informado"
    )
    public ResponseEntity<Diarias> get(@PathVariable("id") Long id) {
        Diarias registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_EXTENDED).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Escolher uma Diaria",
        description = "Escolher uma diaria"
    )
    public ResponseEntity<Diarias> insert(@RequestBody Diarias objeto) {
        Diarias registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar uma Diaria",
        description = "Atualizar uma Diaria Já existente"
    )
    public ResponseEntity<Diarias> update(@RequestBody Diarias objeto) {
        Diarias registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar uma Diaria",
        description = "Deleta uma Diaria com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
}
