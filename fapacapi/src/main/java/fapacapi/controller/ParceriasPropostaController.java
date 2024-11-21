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

import fapacapi.model.ParceriasProposta;

import fapacapi.service.ParceriasPropostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/parceriasProposta", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "parcerias Proposta",
    description = "Endpoints para gerenciar Parcerias de Propostas"
)

public class ParceriasPropostaController implements IController<ParceriasProposta> {

    private final ParceriasPropostaService servico;

    public ParceriasPropostaController(ParceriasPropostaService servico){

        this.servico = servico;
    }

    @Override
    @GetMapping("/")
    @Operation(
        summary = "Obter  Parcerias Propostas ou filtar por termo busca",
        description = "Obtém todas as Parcerias Propostas"
    )
    public ResponseEntity<Page<ParceriasProposta>> get(
            @RequestParam(required = false) String termoBusca, 
            @RequestParam(required = false, defaultValue = "false") boolean unpaged, 
            @SortDefault.SortDefaults({
                @SortDefault(sort = "ParceriasProposta", direction = Sort.Direction.ASC)
            }) 
            @ParameterObject Pageable page) {
        if (unpaged){
            page = Pageable.unpaged();
        }
        Page<ParceriasProposta> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Parcerias Proposta encontrada"),
        @ApiResponse(
            responseCode = "404",
            description = "Parcerias Proposta não encontrada",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obter uma Parcerias Proposta por ID",
        description = "Obtém a Parceria Proposta com o ID infromado"
    )
    public ResponseEntity<ParceriasProposta> get(@PathVariable("id") Long id) {
        ParceriasProposta registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar uma  Parceria Proposta",
        description = "Cadastra uma nova Parceria Proposta"
    )
    public ResponseEntity<ParceriasProposta> insert(@RequestBody ParceriasProposta objeto) {
        ParceriasProposta registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar Parcerias  Propostas",
        description = "Atualiza uma Parcerias Propostas existente"
    )
    public ResponseEntity<ParceriasProposta> update(@RequestBody ParceriasProposta objeto) {
        ParceriasProposta registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar Parcerias Propostas",
        description = "Deleta uma Parcerias Propostas com o Id informado"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
}



