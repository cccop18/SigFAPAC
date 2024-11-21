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


import fapacapi.model.DocPessoal;
import fapacapi.service.DocPessoalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping( value = "/documentoPessoal", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag( 
    name = "DocumentoPessoal",
    description = "Endpoints para gerenciar os Documentos Pessoais"
)
public class DocPessoalController implements IController<DocPessoal> {
   
    private final DocPessoalService servico;

    public DocPessoalController(DocPessoalService servico) {
        this.servico =servico;
    }
     @Override
    @GetMapping("/")
     @Operation(
        summary = "Obtém todos os Documentos Pessoais ou filtra por termo de busca",
        description = "Obtém uma lista paginada de todos os Documentos pessoais cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<DocPessoal>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "nomeDocPessoal", direction = Sort.Direction.ASC)
        })
        @ParameterObject Pageable page) {
        Page<DocPessoal> registros = servico.get(termoBusca, page); 
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Documento pessoal encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "Documento pessoal não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obtém o Documento pessoal por ID",
        description = "Obtém o Documentos Pessoais com o ID informado"
    )
    public ResponseEntity<DocPessoal> get(@PathVariable("id") Long id) {
        DocPessoal registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar um Documento Pessoal",
        description = "Cadastra um novo Documento Pessoal"
    )
    public ResponseEntity<DocPessoal> insert(@RequestBody DocPessoal objeto) {
    DocPessoal registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar um Documento Pessoal",
        description = "Atualiza um Documento Pessoal"
    )
    public ResponseEntity<DocPessoal> update(@RequestBody DocPessoal objeto) {
        DocPessoal registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar Documento Pessoal",
        description = "Deleta o Documento Pessoal com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}

