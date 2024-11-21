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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import fapacapi.model.PesquisadorConhecimento;
import fapacapi.service.PesquisadorConhecimentoService;

@RestController
@RequestMapping(value = "/pesquisadorConhecimento", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "PesquisadorConhecimento",
    description = "Endpoints para gerenciar os pesquisadores conhecimentos"
)
public class PesquisadorConhecimentoController implements IController<PesquisadorConhecimento>{
    private final PesquisadorConhecimentoService servico;

    public PesquisadorConhecimentoController(PesquisadorConhecimentoService servico) {
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
    @Operation(
        summary = "Obtém todos os pesquisadores conhecimentos ou filtrado por termo de busca",
        description = "Obtém uma lista paginada de todos os pesquisadores conhecimentos cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<PesquisadorConhecimento>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "idPesquisadorConhecimento", direction = Sort.Direction.ASC)
        })
        @ParameterObject Pageable page) {
        Page<PesquisadorConhecimento> registros = servico.get(termoBusca, page); 
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pesquisador conhecimento encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "Pesquisador conhecimento não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obtém o pesquisador conhecimento por ID",
        description = "Obtém o pesquisador conhecimento com o ID informado"
    )
    public ResponseEntity<PesquisadorConhecimento> get(@PathVariable("id") Long id) {
        PesquisadorConhecimento registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar um novo pesquisador conhecimento",
        description = "Cadastra um novo pesquisador conhecimento"
    )
    public ResponseEntity<PesquisadorConhecimento> insert(@RequestBody PesquisadorConhecimento objeto) {
        PesquisadorConhecimento registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar um pesquisador conhecimento existente",
        description = "Atualiza as informações de um pesquisador conhecimento existente"
    )
    public ResponseEntity<PesquisadorConhecimento> update(@RequestBody PesquisadorConhecimento objeto) {
        PesquisadorConhecimento registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar um pesquisador conhecimento",
        description = "Deleta o pesquisador conhecimento com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
