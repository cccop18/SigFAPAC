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


import fapacapi.model.PessoalProposta;
import fapacapi.service.PessoalPropostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/pessoalproposta", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Pessoal Proposta",
    description = "Endpoints para gerenciar Pessoal Proposta"
)

public class PessoalPropostaController implements IController<PessoalProposta> {

    private final PessoalPropostaService servico;

    public PessoalPropostaController(PessoalPropostaService servico){
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
    @Operation(
        summary = "Obter Pessoal Proposta ou filtrar por termo busca",
        description = "Obtém todas as Propostas Pessoais"
    )
    public ResponseEntity<Page<PessoalProposta>> get(
            @RequestParam(required = false) String termoBusca, 
            @RequestParam(required = false, defaultValue = "false") boolean unpaged, 
            @SortDefault.SortDefaults({
                @SortDefault(sort = "pesssoalproposta", direction = Sort.Direction.ASC)
            }) 
            @ParameterObject Pageable page) {
        if (unpaged){
            page = Pageable.unpaged();
        }
        Page<PessoalProposta> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pessoal Proposta encontrada"),
        @ApiResponse(
            responseCode = "404",
            description = "Pessoal Proposta não encontrada",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obter uma Proposta Pessoal por ID",
        description = "Obtém a Proposta Pessoal com o ID informado"
    )
    public ResponseEntity<PessoalProposta> get(@PathVariable("id") Long id) {
        PessoalProposta registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar Pessoal Proposta",
        description = "Cadastra um novo Pessoal Proposta"
    )
    public ResponseEntity<PessoalProposta> insert(@RequestBody PessoalProposta objeto) {
        PessoalProposta registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar Pessoal Proposta",
        description = "Atualiza uma Pessoal Proposta  existente"
    )
    public ResponseEntity<PessoalProposta> update(@RequestBody PessoalProposta objeto) {
        PessoalProposta registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar Pessoal Proposta",
        description = "Deleta uma Pessoal Proposta com o Id informado"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
}






