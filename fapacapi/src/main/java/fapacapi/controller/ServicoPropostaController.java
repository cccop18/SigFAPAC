package fapacapi.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import fapacapi.model.ServicoProposta;
import fapacapi.service.ServicoPropostaService;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/servicoproposta", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Servico Proposta",
    description = "Endpoints para gerenciar Servico Proposta"
)
public class ServicoPropostaController implements IController<ServicoProposta> {

    private final ServicoPropostaService servico;

    public ServicoPropostaController(ServicoPropostaService servico){
        this.servico = servico;
    }

     @Override
    @GetMapping("/")
    @Operation(
        summary = "Obter Servico Proposta ou filtrar por termo busca",
        description = "Obtém todas os Servicos Propostos"
    )
    public ResponseEntity<Page<ServicoProposta>> get(
            @RequestParam(required = false) String termoBusca, 
            @RequestParam(required = false, defaultValue = "false") boolean unpaged, 
            @SortDefault.SortDefaults({
                @SortDefault(sort = "servicoproposta", direction = Sort.Direction.ASC)
            }) 
            @ParameterObject Pageable page) {
        if (unpaged){
            page = Pageable.unpaged();
        }
        Page<ServicoProposta> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Servico Proposta encontrada"),
        @ApiResponse(
            responseCode = "404",
            description = "Servico Proposta não encontrada",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obter um Servico Proposta por ID",
        description = "Obtém um Servico Proposta com o ID informado"
    )
    public ResponseEntity<ServicoProposta> get(@PathVariable("id") Long id) {
        ServicoProposta registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar Servico Proposta",
        description = "Cadastra um novo Servico Proposta"
    )
    public ResponseEntity<ServicoProposta> insert(@RequestBody ServicoProposta objeto) {
        ServicoProposta registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar Servico Proposta",
        description = "Atualiza uma Servico Proposto  existente"
    )
    public ResponseEntity<ServicoProposta> update(@RequestBody ServicoProposta objeto) {
        ServicoProposta registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar Servico Proposta",
        description = "Deleta uma Servico Proposta com o Id informado"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
}









