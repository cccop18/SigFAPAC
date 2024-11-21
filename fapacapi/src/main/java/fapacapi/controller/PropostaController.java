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

import fapacapi.model.Proposta;
import fapacapi.service.PropostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping(value = "/propostas", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "propostas",
    description = "Endpoints para gerenciar propostas"
)
public class PropostaController implements IController<Proposta>{

    private final PropostaService servico;

    public PropostaController(PropostaService servico){
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
    @Operation(
        summary = "Obeter propostas ou filtar por termo busca",
        description = "Obtém todas as propostas"
    )
    public ResponseEntity<Page<Proposta>> get(
        @RequestParam(required = false) String termoBusca, 
        @RequestParam(required = false, defaultValue = "false") boolean unpaged, 
        @SortDefault.SortDefaults({
            @SortDefault(sort = "tituloProposta", direction = Sort.Direction.ASC)
        }) 
        @ParameterObject Pageable page) {
        if (unpaged){
            page = Pageable.unpaged();
        }
        Page<Proposta> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "prposta encontrada"),
        @ApiResponse(
            responseCode = "404",
            description = "proposta não encontrada",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obeter uma proposta por ID",
        description = "Obtém a proposta com o ID infromado"
    )
    public ResponseEntity<Proposta> get(@PathVariable("id") Long id) {
        Proposta registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar proposta",
        description = "Cadastra uma nova proposta"
    )
    public ResponseEntity<Proposta> insert(@RequestBody Proposta objeto) {
        Proposta registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar proposta",
        description = "Atualiza uma proposta existente"
    )
    public ResponseEntity<Proposta> update(@RequestBody Proposta objeto) {
        Proposta registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar proposta",
        description = "Deleta uma proposta com o Id informado"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
}
