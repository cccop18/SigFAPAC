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

import fapacapi.model.TerceiraSubArea;

import fapacapi.service.TerceiraSubAreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping( value = "/terceiraSubArea", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag( 
    name = "Terceira SubArea",
    description = "Endpoints para gerenciar a TerceiraSubArea de pesquisador"
)
public class TerceiraSubAreaController implements IController<TerceiraSubArea>{

    private final TerceiraSubAreaService servico;

    public TerceiraSubAreaController(TerceiraSubAreaService servico){
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
     @Operation(
        summary = "Obtém todas as TerceiraSubareas do pesquisador ou filtra por termo de busca",
        description = "Obtém uma lista paginada de todas as TerceiraSubareas  dos pesquisadores cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<TerceiraSubArea>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "nomeTerceiraSub", direction = Sort.Direction.ASC)
        })
        @ParameterObject Pageable page) {
        Page<TerceiraSubArea> registros = servico.get(termoBusca, page); 
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "TerceiraSubarea"),
        @ApiResponse(
            responseCode = "404",
            description = "TerceiraSubarea",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obtém a TerceiraSubArea do pesquisador por ID",
        description = "Obtém a TerceiraSubArea com o ID informado"
    )
    public ResponseEntity<TerceiraSubArea> get(@PathVariable("id") Long id) {
        TerceiraSubArea registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Escolher uma TerceiraSubArea para o pesquisador",
        description = "escolher uma TerceiraSubArea "
    )
    public ResponseEntity<TerceiraSubArea> insert(@RequestBody TerceiraSubArea objeto) {
        TerceiraSubArea registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar a TerceiraSubArea do pesquisador",
        description = "Atualiza a TerceiraSubArea existente do pesquisador"
    )
    public ResponseEntity<TerceiraSubArea> update(@RequestBody TerceiraSubArea objeto) {
        TerceiraSubArea registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar TerceiraSubArea do pesquisador",
        description = "Deleta a TerceiraSubArea do pesquisador com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}