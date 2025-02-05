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

import fapacapi.model.VinculoProposta;
import fapacapi.service.VinculoPropostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping( value = "/vinculoProposta", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag( 
    name = "vinculoProposta",
    description = "Endpoints para gerenciar os Vinculo da Proposta"
)
public class VinculoPropostaController implements IController<VinculoProposta>{

    private final VinculoPropostaService servico;

    public VinculoPropostaController(VinculoPropostaService servico){
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
     @Operation(
        summary = "Obtém todos os Vinculo da Proposta ou filtra por termo de busca",
        description = "Obtém uma lista paginada de todos os Vinculo da Proposta cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<VinculoProposta>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "vinculoProposta", direction = Sort.Direction.ASC)
        })
        @ParameterObject Pageable page) {
        Page<VinculoProposta> registro = servico.get(termoBusca, page);
        return ResponseEntity.ok(registro);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vinculo da Proposta encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "Vinculo da Proposta não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obtém o Vinculo da Proposta por ID",
        description = "Obtém o Vinculo da Proposta com o ID informado"
    )
    public ResponseEntity<VinculoProposta> get(@PathVariable("id") Long id) {
        VinculoProposta registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar um Vinculo da Proposta",
        description = "Cadastra um novo Vinculo da Proposta"
    )
    public ResponseEntity<VinculoProposta> insert(@RequestBody VinculoProposta objeto) {
        VinculoProposta registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar um Vinculo da Proposta",
        description = "Atualiza um Vinculo da Proposta"
    )
    public ResponseEntity<VinculoProposta> update(@RequestBody VinculoProposta objeto) {
        VinculoProposta registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar Vinculo da Proposta",
        description = "Deleta o Vinculo da Proposta com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
}
