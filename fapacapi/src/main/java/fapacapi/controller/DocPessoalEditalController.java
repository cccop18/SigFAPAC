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
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import fapacapi.model.DocPessoalEdital;
import fapacapi.service.DocPessoalEditalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping( value = "/docpessoaledital", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag( 
    name = "DocpessoalEdital",
    description = "Endpoints para gerenciar os Documentos pessoais editais"
)
public class DocPessoalEditalController implements IController<DocPessoalEdital> {

    private final DocPessoalEditalService servico;

    public DocPessoalEditalController(DocPessoalEditalService servico){
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
     @Operation(
        summary = "Obtém todos os documentos Pessoais e editais ou filtra por termo de busca",
        description = "Obtém uma lista paginada de todos os documentos Pessoais editais cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<DocPessoalEdital>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @ParameterObject Pageable page) {
        Page<DocPessoalEdital> registros = servico.get(termoBusca, page); 
        return ResponseEntity.ok(registros);
    }
          
    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Documento pessoal edital encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "Documento pessoal edital não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obtém o DocPessoalEdital por ID",
        description = "Obtém o DocPessoalEdital com o ID informado"
    )
    public ResponseEntity<DocPessoalEdital> get(@PathVariable("id") Long id) {
        DocPessoalEdital registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar um DocPessoalEdital",
        description = "Cadastra um novo DocPessoalEdital"
    )
    public ResponseEntity<DocPessoalEdital> insert(@RequestBody DocPessoalEdital objeto) {
    DocPessoalEdital registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar um DocpessoalEdital",
        description = "Atualiza um DocPessoalEdital"
    )
    public ResponseEntity<DocPessoalEdital> update(@RequestBody DocPessoalEdital objeto) {
        DocPessoalEdital registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar docPessoalEdital",
        description = "Deleta o DocPessoalEdital com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}






