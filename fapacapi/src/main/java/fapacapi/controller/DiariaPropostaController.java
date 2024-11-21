package fapacapi.controller;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


import fapacapi.model.DiariaProposta;
import fapacapi.service.DiariaPropostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/diariaproposta", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Abrangencia proposta",
    description = "Endpoints para gerenciar a abrangencia proposta do pesquisador"
)
public class DiariaPropostaController implements IController<DiariaProposta> {

    private final DiariaPropostaService servico;

    public DiariaPropostaController(DiariaPropostaService servico){
        this.servico = servico;
    }


   @Override
    @GetMapping("/")
     @Operation(
        summary = "Obtém todos as Diarias Propostas ou filtra por termo de busca",
        description = "Obtém uma lista paginada de todas as Diarias Propostas cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<DiariaProposta>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        
        @ParameterObject Pageable page) {
        Page<DiariaProposta> registros = servico.get(termoBusca, page); 
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Diaria Proposta  encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "Diaria Proposta não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obtém a Diaria Proposta por ID",
        description = "Obtém a Diaria Proposta com o ID informado"
    )
    public ResponseEntity<DiariaProposta> get(@PathVariable("id") Long id) {
        DiariaProposta registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar uma Diaria Proposta",
        description = "Cadastra uma nova Diaria Proposta "
    )
    public ResponseEntity<DiariaProposta> insert(@RequestBody DiariaProposta objeto) {
    DiariaProposta registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar uma Diaria Proposta",
        description = "Atualiza uma Diaria Proposta"
    )
    public ResponseEntity<DiariaProposta> update(@RequestBody DiariaProposta objeto) {
        DiariaProposta registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar uma Diaria Proposta",
        description = "Deleta uma diaria proposta com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}







