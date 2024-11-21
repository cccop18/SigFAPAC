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

import fapacapi.controller.dto.PropostaConhecimentoDto;
import fapacapi.controller.mapper.PropostaConhecimentoMapper;
import fapacapi.model.PropostaConhecimento;
import fapacapi.service.PropostaConhecimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/PropostaConhecimentos", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "PropostaConhecimentos",
    description = "Endpoints para gerenciar PropostaConhecimentos"
)
public class PropostaConhecimentoController{

    private final PropostaConhecimentoService servico;
    private final PropostaConhecimentoMapper mapper;

    public PropostaConhecimentoController(PropostaConhecimentoService servico, PropostaConhecimentoMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @GetMapping("/")
    @Operation(
        summary = "Obeter PropostaConhecimentos ou filtar por termo busca",
        description = "Obtém todas as PropostaConhecimentos"
    )
    public ResponseEntity<Page<PropostaConhecimento>> get(
            @RequestParam(required = false) String termoBusca, 
            @RequestParam(required = false, defaultValue = "false") boolean unpaged, 
            @SortDefault.SortDefaults({
                @SortDefault(sort = "nomeInstituicao", direction = Sort.Direction.ASC)
            }) 
            @ParameterObject Pageable page) {
        if (unpaged){
            page = Pageable.unpaged();
        }
        Page<PropostaConhecimento> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
    }
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "PropostaConhecimento encontrada"),
        @ApiResponse(
            responseCode = "404",
            description = "PropostaConhecimento não encontrada",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obeter uma PropostaConhecimento por ID",
        description = "Obtém a PropostaConhecimento com o ID infromado"
    )
    public ResponseEntity<PropostaConhecimento> get(@PathVariable("id") Long id) {
        PropostaConhecimento registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @PostMapping("/")
    @Operation(
        summary = "Cadastrar PropostaConhecimento",
        description = "Cadastra uma nova PropostaConhecimento"
    )
    public ResponseEntity<PropostaConhecimentoDto> insert(@RequestBody @Valid PropostaConhecimentoDto objeto) {
        PropostaConhecimento objetoConvertido = mapper.toEntity(objeto);
        PropostaConhecimento registro = servico.save(objetoConvertido);
        PropostaConhecimentoDto dto = mapper.tDto(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    @PutMapping("/")
    @Operation(
        summary = "Atualizar PropostaConhecimento",
        description = "Atualiza uma PropostaConhecimento existente"
    )
    public ResponseEntity<PropostaConhecimento> update(@RequestBody PropostaConhecimento objeto) {
        PropostaConhecimento registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar PropostaConhecimento",
        description = "Deleta uma PropostaConhecimento com o Id informado"
    )
    
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    
    
}
