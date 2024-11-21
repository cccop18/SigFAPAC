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

import fapacapi.model.ArquivoEdital;
import fapacapi.service.ArquivoEditalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/arquivoEdital", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Arquivo Edital",
    description = "Endpoints para gerenciar arquivo edital"
)
public class ArquivoEditalController implements IController<ArquivoEdital>{

    private final ArquivoEditalService servico;

    public ArquivoEditalController(ArquivoEditalService servico) {
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
    @Operation(
        summary = "Obter abrangência ou filtrar por termo de busca",
        description = "Obtém todas as abrangências"
    )
    public ResponseEntity<Page<ArquivoEdital>> get(
            @RequestParam(required = false) String termoBusca, 
            @RequestParam(required = false, defaultValue = "false") boolean unpaged, 
            @SortDefault.SortDefaults({
                @SortDefault(sort = "idArquivoEdital", direction = Sort.Direction.ASC)
            }) 
            @ParameterObject Pageable page) {
        if (unpaged){
            page = Pageable.unpaged();
        }
        Page<ArquivoEdital> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
    
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "ArquivoEdital encontrada"),
        @ApiResponse(
            responseCode = "404",
            description = "ArquivoEdital não encontrada",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obeter uma ArquivoEdital por ID",
        description = "Obtém a ArquivoEdital com o ID infromado"
    )
    public ResponseEntity<ArquivoEdital> get(@PathVariable("id") Long id) {
        ArquivoEdital registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar ArquivoEdital",
        description = "Cadastra uma nova ArquivoEdital"
    )
    public ResponseEntity<ArquivoEdital> insert(@RequestBody ArquivoEdital objeto) {
        ArquivoEdital registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar ArquivoEdital",
        description = "Atualiza uma ArquivoEdital existente"
    )
    public ResponseEntity<ArquivoEdital> update(@RequestBody ArquivoEdital objeto) {
        ArquivoEdital registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar ArquivoEdital",
        description = "Deleta uma ArquivoEdital com o Id informado"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
}