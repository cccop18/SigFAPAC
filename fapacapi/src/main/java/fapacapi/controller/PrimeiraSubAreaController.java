package fapacapi.controller;

import java.util.List;
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

import fapacapi.model.PrimeiraSubArea;

import fapacapi.service.PrimeiraSubAreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping( value = "/primeiraSubArea", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag( 
    name = "Primeira SubArea",
    description = "Endpoints para gerenciar a PrimeiraSubArea de pesquisador"
)
public class PrimeiraSubAreaController implements IController<PrimeiraSubArea>{

    private final PrimeiraSubAreaService servico;

    public PrimeiraSubAreaController(PrimeiraSubAreaService servico){
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
     @Operation(
        summary = "Obtém todas as PrimeiraSubareas do pesquisador ou filtra por termo de busca",
        description = "Obtém uma lista paginada de todas as PrimeiraSubareas  dos pesquisadores cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<PrimeiraSubArea>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "nomePrimeiraSub", direction = Sort.Direction.ASC)
        })
        @ParameterObject Pageable page) {
        Page<PrimeiraSubArea> registros = servico.get(termoBusca, page); 
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "PrimeiraSubarea"),
        @ApiResponse(
            responseCode = "404",
            description = "PrimeiraSubarea",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obtém a PrimeiraSubArea do pesquisador por ID",
        description = "Obtém a PrimeiraSubArea com o ID informado"
    )
    public ResponseEntity<PrimeiraSubArea> get(@PathVariable("id") Long id) {
        PrimeiraSubArea registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Escolher uma PrimeiraSubArea para o pesquisador",
        description = "escolher uma PrimeiraSubArea "
    )
    public ResponseEntity<PrimeiraSubArea> insert(@RequestBody PrimeiraSubArea objeto) {
        PrimeiraSubArea registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar a PrimeiraSubArea do pesquisador",
        description = "Atualiza a PrimeiraSubArea existente do pesquisador"
    )
    public ResponseEntity<PrimeiraSubArea> update(@RequestBody PrimeiraSubArea objeto) {
        PrimeiraSubArea registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar PrimeiraSubArea do pesquisador",
        description = "Deleta a PrimeiraSubArea do pesquisador com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @PostMapping("/{fk}")
    @Operation(
            summary = "Obtém a PrimeiraSubArea do pesquisador por ID",
            description = "Obtém a PrimeiraSubArea com o ID informado"
        )
        public ResponseEntity<List<PrimeiraSubArea>> getFk(@PathVariable("fk") Long fk) {
            List<PrimeiraSubArea> registros = servico.getFk(fk);
            if (registros == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(registros);
        }
}


