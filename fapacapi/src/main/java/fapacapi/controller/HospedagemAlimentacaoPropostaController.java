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


import fapacapi.model.HospedagemAlimentacaoProposta;
import fapacapi.service.HospedagemAlimentacaoPropostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/hospedagemalimentacaoproposta", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Hospedagem Alimentacao Proposta",
    description = "Endpoints para gerenciar a hospedagem alimentação proposta do pesquisador"
)


public class HospedagemAlimentacaoPropostaController implements IController<HospedagemAlimentacaoProposta> {

    private final HospedagemAlimentacaoPropostaService servico;

    public HospedagemAlimentacaoPropostaController(HospedagemAlimentacaoPropostaService servico){
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
     @Operation(
        summary = "Obtém todos as Proposta de Alimentação na Hospedagem ou filtra por termo de busca",
        description = "Obtém uma lista paginada de todas as propostas de alimentação na hospedagem cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<HospedagemAlimentacaoProposta>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        
        @ParameterObject Pageable page) {
        Page<HospedagemAlimentacaoProposta> registros = servico.get(termoBusca, page); 
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proposta de alimentaçao na Hospedagem  encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "Proposta de alimentação na Hospedagem não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obtém a Proposta de Alimentação por ID",
        description = "Obtém a Proposta de Alimentação da Hospedagem com o ID informado"
    )
    public ResponseEntity<HospedagemAlimentacaoProposta> get(@PathVariable("id") Long id) {
        HospedagemAlimentacaoProposta registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar uma Proposta de Alimentação Hospedagem ",
        description = "Cadastra uma nova Proposta de Alimentação "
    )
    public ResponseEntity<HospedagemAlimentacaoProposta> insert(@RequestBody HospedagemAlimentacaoProposta objeto) {
    HospedagemAlimentacaoProposta registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar uma Prosposta de Hospedagem de Alimentação",
        description = "Atualiza uma Proposta de Alimentação de Hospedagem"
    )
    public ResponseEntity<HospedagemAlimentacaoProposta> update(@RequestBody HospedagemAlimentacaoProposta objeto) {
        HospedagemAlimentacaoProposta registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar Proposta de Alimentação da Hospedagem ",
        description = "Deleta uma Proposta de Alimentação de Hospedagem com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}







