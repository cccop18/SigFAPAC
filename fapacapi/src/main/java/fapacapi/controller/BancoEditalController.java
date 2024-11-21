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


import fapacapi.model.BancoEdital;
import fapacapi.service.BancoEditalService;

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
@RequestMapping( value = "/bancoedital", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag( 
    name = "BancoEdital",
    description = "Endpoints para gerenciar os Bancos dos editais"
)
public class BancoEditalController implements IController<BancoEdital> {

    private final BancoEditalService servico;

    public BancoEditalController(BancoEditalService servico){
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
     @Operation(
        summary = "Obtém todos os bancos editais ou filtra por termo de busca",
        description = "Obtém uma lista paginada de todos os bancos editais cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<BancoEdital>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @ParameterObject Pageable page) {
        Page<BancoEdital> registros = servico.get(termoBusca, page); 
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Banco edital encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "Banco edital não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obtém o banco edital por ID",
        description = "Obtém o banco edital com o ID informado"
    )
    public ResponseEntity<BancoEdital> get(@PathVariable("id") Long id) {
        BancoEdital registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar um Banco edital",
        description = "Cadastra um novo Banco edital"
    )
    public ResponseEntity<BancoEdital> insert(@RequestBody BancoEdital objeto) {
    BancoEdital registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar um Banco edital",
        description = "Atualiza um Banco edital"
    )
    public ResponseEntity<BancoEdital> update(@RequestBody BancoEdital objeto) {
        BancoEdital registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar banco edital",
        description = "Deleta o banco edital com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}




