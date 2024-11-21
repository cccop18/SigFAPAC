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

import fapacapi.model.BolsaProposta;

import fapacapi.service.BolsaPropostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/bolsaproposta", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Bolsa Proposta",
    description = "Endpoints para gerenciar a Bolsa Proposta do pesquisador"
)

public class BolsaPropostaController  implements IController<BolsaProposta>{
    
    private final BolsaPropostaService servico;

    public BolsaPropostaController(BolsaPropostaService servico){
        this.servico = servico;
    }


    @Override
    @GetMapping("/")
     @Operation(
        summary = "Obtém todos os Bolsa Proposta ou filtra por termo de busca",
        description = "Obtém uma lista paginada de todos os Bolsa Proposta cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<BolsaProposta>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        
        @ParameterObject Pageable page) {
        Page<BolsaProposta> registros = servico.get(termoBusca, page); 
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bolsa proposta  encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "Bolsa Proposta não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obtém o Bolsa Proposta por ID",
        description = "Obtém o Bolsa Permissão Proposta com o ID informado"
    )
    public ResponseEntity<BolsaProposta> get(@PathVariable("id") Long id) {
        BolsaProposta registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar um Bolsa Proposta",
        description = "Cadastra uma nova Bolsa Proposta "
    )
    public ResponseEntity<BolsaProposta> insert(@RequestBody BolsaProposta objeto) {
    BolsaProposta registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar um Bolsa Proposta",
        description = "Atualiza um Bolsa Proposta"
    )
    public ResponseEntity<BolsaProposta> update(@RequestBody BolsaProposta objeto) {
        BolsaProposta registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar um Bolsa Proposta",
        description = "Deleta um Bolsa Proposta com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}















