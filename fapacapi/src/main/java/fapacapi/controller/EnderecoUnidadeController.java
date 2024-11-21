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

import fapacapi.model.EnderecoUnidade;
import fapacapi.service.EnderecoUnidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping( value = "/enderecoUnidade", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "EnderecoUnidade",
    description = "Endpoints para Endereços de Unidades"
)
public class EnderecoUnidadeController implements IController<EnderecoUnidade>{

    private final EnderecoUnidadeService servico;

    public EnderecoUnidadeController(EnderecoUnidadeService servico){
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
    @Operation(
        summary = "Obtém os endereços de unidades ou filtrar por termo de busca",
        description = "Obtém uma lista paginada de todos os endereços das unidades cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<EnderecoUnidade>> get(
        @RequestParam(required = false) String termoBusca,  
        @RequestParam(required = false, defaultValue = "false")boolean unpaged, 
        @SortDefault.SortDefaults({
            @SortDefault(sort = "bairroEnderecoUnidade", direction = Sort.Direction.ASC)
        })
       @ParameterObject Pageable page) {
        if (unpaged) {
            page = Pageable.unpaged();
        }
       Page<EnderecoUnidade> registros = servico.get(termoBusca, page);
       return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Endereço unidade encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "Endereço unidade não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obter endereço de unidade por ID",
        description = "Obtém o endereço de unidade com o ID informado"
    )
    public ResponseEntity<EnderecoUnidade> get(@PathVariable("id") Long id) {
        EnderecoUnidade registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar endereco da unidade",
        description = "Cadastra um novo endereco para a unidade"
    )
    public ResponseEntity<EnderecoUnidade> insert(@RequestBody EnderecoUnidade objeto) {
        EnderecoUnidade registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar endereço de unidade",
        description = "Atualiza um endereço de unidade existente"
    )
    public ResponseEntity<EnderecoUnidade> update(@RequestParam EnderecoUnidade objeto) {
        EnderecoUnidade registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar endereço de unidade",
        description = "Deleta o endereco da unidade com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


}