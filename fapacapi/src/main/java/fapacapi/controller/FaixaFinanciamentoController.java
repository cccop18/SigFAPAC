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


import fapacapi.model.FaixaFinanciamento;
import fapacapi.service.FaixaFinanciamentoService;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping( value = "/faixafinanciamento", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag( 
    name = "FaixaFinanciamento",
    description = "Endpoints para gerenciar as Faixas de Financiamentos"
)
public class FaixaFinanciamentoController implements IController<FaixaFinanciamento> {



    private final FaixaFinanciamentoService servico;
    
    public FaixaFinanciamentoController(FaixaFinanciamentoService servico){
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
     @Operation(
        summary = "Obtém todas as Faixas de Financiamento ou filtra por termo de busca",
        description = "Obtém uma lista paginada de todas as Faixas Financiamento cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<FaixaFinanciamento>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @ParameterObject Pageable page) {
        Page<FaixaFinanciamento> registros = servico.get(termoBusca, page); 
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Faixa de Financiamento encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "Faixa de Financiamento não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obtém a Faixa de Financiamento por ID",
        description = "Obtém a Faixa de Financiamento com o ID informado"
    )
    public ResponseEntity<FaixaFinanciamento> get(@PathVariable("id") Long id) {
        FaixaFinanciamento registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar uma Faixa de Financiamento",
        description = "Cadastra uma nova Faixa de Financiamento"
    )
    public ResponseEntity<FaixaFinanciamento> insert(@RequestBody FaixaFinanciamento objeto) {
    FaixaFinanciamento registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar uma Faixa de Financiamento",
        description = "Atualiza uma Faixa de Financiamento"
    )
    public ResponseEntity<FaixaFinanciamento> update(@RequestBody FaixaFinanciamento objeto) {
        FaixaFinanciamento registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar uma Faixa de Financiamento",
        description = "Deleta uma Faixa de Financiamento com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}







