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


import fapacapi.model.MaterialPermProposta;
import fapacapi.service.MaterialPermPropostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/materialpermproposta", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Material Permissâo Proposta",
    description = "Endpoints para gerenciar o Material Permissão Proposta do pesquisador"
)

public class MaterialPermPropostaController implements IController<MaterialPermProposta> {

    private final MaterialPermPropostaService servico;


    public MaterialPermPropostaController(MaterialPermPropostaService servico){
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
     @Operation(
        summary = "Obtém todos os Material Permissão Proposta ou filtra por termo de busca",
        description = "Obtém uma lista paginada de todas os Material Permissão Proposta cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<MaterialPermProposta>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        
        @ParameterObject Pageable page) {
        Page<MaterialPermProposta> registros = servico.get(termoBusca, page); 
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Material permissão proposta  encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "Material Permissão Proposta não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obtém o Material Permissão Proposta por ID",
        description = "Obtém o Material Permissão Proposta com o ID informado"
    )
    public ResponseEntity<MaterialPermProposta> get(@PathVariable("id") Long id) {
        MaterialPermProposta registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar um Material Permissão Proposta",
        description = "Cadastra um novo Material PermissÃo Proposta "
    )
    public ResponseEntity<MaterialPermProposta> insert(@RequestBody MaterialPermProposta objeto) {
    MaterialPermProposta registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar um Material Permissão Proposta",
        description = "Atualiza um Material Permissão Proposta"
    )
    public ResponseEntity<MaterialPermProposta> update(@RequestBody MaterialPermProposta objeto) {
        MaterialPermProposta registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar um Material Permissão Proposta",
        description = "Deleta um Material Permissão Proposta com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}













