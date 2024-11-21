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

import fapacapi.model.OrcamentoProposta;
import fapacapi.service.OrcamentoPropostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/orcamentoProposta", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "OrcamentoProposta",
    description = "Endpoints para gerenciar orçamentos"
)
public class OrcamentoPropostaController implements IController<OrcamentoProposta>{

    private final OrcamentoPropostaService servico;

    public OrcamentoPropostaController(OrcamentoPropostaService servico) {
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
    @Operation(
        summary = "Obeter moeda ou filtar por termo busca",
        description = "Obtém todas as moedas"
    )
    public ResponseEntity<Page<OrcamentoProposta>> get(
            @RequestParam(required = false) String termoBusca, 
            @RequestParam(required = false, defaultValue = "false") boolean unpaged, 
            @SortDefault.SortDefaults({
                @SortDefault(sort = "nomeMoeda", direction = Sort.Direction.ASC)
            }) 
            @ParameterObject Pageable page) {
        if (unpaged){
            page = Pageable.unpaged();
        }
        Page<OrcamentoProposta> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
    
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OrcamentoProposta encontrada"),
        @ApiResponse(
            responseCode = "404",
            description = "OrcamentoProposta não encontrada",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obeter uma OrcamentoProposta por ID",
        description = "Obtém a OrcamentoProposta com o ID infromado"
    )
    public ResponseEntity<OrcamentoProposta> get(@PathVariable("id") Long id) {
        OrcamentoProposta registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar OrcamentoProposta",
        description = "Cadastra uma nova OrcamentoProposta"
    )
    public ResponseEntity<OrcamentoProposta> insert(@RequestBody OrcamentoProposta objeto) {
        OrcamentoProposta registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar OrcamentoProposta",
        description = "Atualiza uma OrcamentoProposta existente"
    )
    public ResponseEntity<OrcamentoProposta> update(@RequestBody OrcamentoProposta objeto) {
        OrcamentoProposta registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar OrcamentoProposta",
        description = "Deleta uma OrcamentoProposta com o Id informado"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
}