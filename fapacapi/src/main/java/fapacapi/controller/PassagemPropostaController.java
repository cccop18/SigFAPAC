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


import fapacapi.model.PassagemProposta;
import fapacapi.service.PassagemPropostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/passagemproposta", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "passagem proposta",
    description = "Endpoints para gerenciar Passagem Proposta"
)

public class PassagemPropostaController implements IController<PassagemProposta> {

    private final PassagemPropostaService servico;

    public PassagemPropostaController(PassagemPropostaService servico){
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
    @Operation(
        summary = "Obter Passagem Proposta ou filtrar por termo busca",
        description = "Obtém todas as passagens Propostas"
    )
    public ResponseEntity<Page<PassagemProposta>> get(
            @RequestParam(required = false) String termoBusca, 
            @RequestParam(required = false, defaultValue = "false") boolean unpaged, 
            @SortDefault.SortDefaults({
                @SortDefault(sort = "passagemproposta", direction = Sort.Direction.ASC)
            }) 
            @ParameterObject Pageable page) {
        if (unpaged){
            page = Pageable.unpaged();
        }
        Page<PassagemProposta> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Passagem Proposta encontrada"),
        @ApiResponse(
            responseCode = "404",
            description = "Passagem Proposta não encontrada",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obter uma Passagem Proposta por ID",
        description = "Obtém a Passagem Proposta com o ID informado"
    )
    public ResponseEntity<PassagemProposta> get(@PathVariable("id") Long id) {
        PassagemProposta registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar Passagem Proposta",
        description = "Cadastra uma nova Passagem Proposta"
    )
    public ResponseEntity<PassagemProposta> insert(@RequestBody PassagemProposta objeto) {
        PassagemProposta registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar Passagem Proposta",
        description = "Atualiza uma Passagem Proposta  existente"
    )
    public ResponseEntity<PassagemProposta> update(@RequestBody PassagemProposta objeto) {
        PassagemProposta registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar Passagem Proposta",
        description = "Deleta uma Passagem Proposta com o Id informado"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
}



