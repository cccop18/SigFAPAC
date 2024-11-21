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

import fapacapi.model.EnderecoPesquisador;
import fapacapi.service.EnderecoPesquisadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping( value = "/enderecoPesquisador", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag( 
    name = "Endereco de pesquisador",
    description = "Endpoints para gerenciar os endereços de pesquisador"
)
public class EnderecoPesquisadorController implements IController<EnderecoPesquisador>{

    private final EnderecoPesquisadorService servico;

    public EnderecoPesquisadorController(EnderecoPesquisadorService servico){
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
     @Operation(
        summary = "Obtém todos os endereços de pesquisador ou filtrado por termo de busca",
        description = "Obtém uma lista paginada de todos os endereços dos pesquisadores cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<EnderecoPesquisador>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "bairroEnderecoPesquisador", direction = Sort.Direction.ASC)
        })
        @ParameterObject Pageable page) {
        Page<EnderecoPesquisador> registros = servico.get(termoBusca, page); 
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Endereco do pesquisador encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "Endereço do pesquisador não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obtém o endereço do pesquisador por ID",
        description = "Obtém o endereço com o ID informado"
    )
    public ResponseEntity<EnderecoPesquisador> get(@PathVariable("id") Long id) {
        EnderecoPesquisador registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar um endereço para o pesquisador",
        description = "Cadastra um novo endereço para o pesquisador"
    )
    public ResponseEntity<EnderecoPesquisador> insert(@RequestBody EnderecoPesquisador objeto) {
        EnderecoPesquisador registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar um endereco do pesquisador",
        description = "Atualiza um endereço existente do pesquisador"
    )
    public ResponseEntity<EnderecoPesquisador> update(@RequestBody EnderecoPesquisador objeto) {
        EnderecoPesquisador registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar endereço do pesquisador",
        description = "Deleta o endereço do pesquisador com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
