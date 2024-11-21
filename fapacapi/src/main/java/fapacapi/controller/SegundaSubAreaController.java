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

import fapacapi.model.SegundaSubArea;

import fapacapi.service.SegundaSubAreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping( value = "/segundaSubArea", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag( 
    name = "Segunda SubArea",
    description = "Endpoints para gerenciar a SegundaSubArea de pesquisador"
)
public class SegundaSubAreaController implements IController<SegundaSubArea>{

    private final SegundaSubAreaService servico;

    public SegundaSubAreaController(SegundaSubAreaService servico){
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
     @Operation(
        summary = "Obtém todas as SegundaSubareas do pesquisador ou filtra por termo de busca",
        description = "Obtém uma lista paginada de todas as SegundaSubareas  dos pesquisadores cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<SegundaSubArea>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "nomeSegundaSub", direction = Sort.Direction.ASC)
        })
        @ParameterObject Pageable page) {
        Page<SegundaSubArea> registros = servico.get(termoBusca, page); 
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "SegundaSubarea"),
        @ApiResponse(
            responseCode = "404",
            description = "SegundaSubarea",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obtém a SegundaSubArea do pesquisador por ID",
        description = "Obtém a PrimeiraSubArea com o ID informado"
    )
    public ResponseEntity<SegundaSubArea> get(@PathVariable("id") Long id) {
        SegundaSubArea registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Escolher uma SegundaSubArea para o pesquisador",
        description = "escolher uma SegundaSubArea "
    )
    public ResponseEntity<SegundaSubArea> insert(@RequestBody SegundaSubArea objeto) {
        SegundaSubArea registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar a SegundaSubArea do pesquisador",
        description = "Atualiza a SegundaSubArea existente do pesquisador"
    )
    public ResponseEntity<SegundaSubArea> update(@RequestBody SegundaSubArea objeto) {
        SegundaSubArea registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar SegundaSubArea do pesquisador",
        description = "Deleta a SegundaSubArea do pesquisador com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}