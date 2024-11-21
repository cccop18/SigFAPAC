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

import fapacapi.model.MaterialProposta;

import fapacapi.service.MaterialPropostaService;

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
@RequestMapping(value = "/materialproposta", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "materialproposta",
    description = "Endpoints para gerenciar Material Proposta"
)
public class MaterialPropostaController implements IController<MaterialProposta> {

    private final MaterialPropostaService servico;

    public MaterialPropostaController(MaterialPropostaService servico){
        this.servico = servico;
    }

     @Override
    @GetMapping("/")
    @Operation(
        summary = "Obter  Material de Proposta ou filtrar por termo busca",
        description = "Obtém todos os Membros"
    )
    public ResponseEntity<Page<MaterialProposta>> get(
            @RequestParam(required = false) String termoBusca, 
            @RequestParam(required = false, defaultValue = "false") boolean unpaged, 
            @SortDefault.SortDefaults({
                @SortDefault(sort = "materialproposta", direction = Sort.Direction.ASC)
            }) 
            @ParameterObject Pageable page) {
        if (unpaged){
            page = Pageable.unpaged();
        }
        Page<MaterialProposta> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Material Proposta encontrada"),
        @ApiResponse(
            responseCode = "404",
            description = "Material Proposta não encontrada",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obter Material Proposta por ID",
        description = "Obtém um Material Proposta com o ID informado"
    )
    public ResponseEntity<MaterialProposta> get(@PathVariable("id") Long id) {
        MaterialProposta registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar um Material Proposto",
        description = "Cadastra um Novo  Material Proposto"
    )
    public ResponseEntity<MaterialProposta> insert(@RequestBody MaterialProposta objeto) {
        MaterialProposta registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar Material Proposta",
        description = "Atualiza Material Proposta existentes"
    )
    public ResponseEntity<MaterialProposta> update(@RequestBody MaterialProposta objeto) {
        MaterialProposta registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar um Material Proposta",
        description = "Deleta um Material proposta com o Id informado"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
}





    




