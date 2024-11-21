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

import fapacapi.controller.dto.PesquisadorEstrangeiroDto;
import fapacapi.controller.mapper.PesquisadorEstrangeiroMapper;
import fapacapi.model.Pesquisador;
import fapacapi.service.PesquisadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/pesquisador", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Pesquisador",
    description = "Endpoints para gerenciar os pesquisadores"
)
public class PesquisadorController implements IController<Pesquisador> {

    private final PesquisadorService servico;
    private final PesquisadorEstrangeiroMapper mapper;

    public PesquisadorController(PesquisadorService servico, PesquisadorEstrangeiroMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @Override
    @GetMapping("/")
    @Operation(
        summary = "Obtém todos os pesquisadores ou filtrado por termo de busca",
        description = "Obtém uma lista paginada de todos os pesquisadores cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<Pesquisador>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "nomeCompletoPesquisador", direction = Sort.Direction.ASC)
        })
        @ParameterObject Pageable page) {
        Page<Pesquisador> registros = servico.get(termoBusca, page); 
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pesquisador encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "Pesquisador não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obtém o pesquisador por ID",
        description = "Obtém o pesquisador com o ID informado"
    )
    public ResponseEntity<Pesquisador> get(@PathVariable("id") Long id) {
        Pesquisador registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar um novo pesquisador",
        description = "Cadastra um novo pesquisador"
    )
    public ResponseEntity<Pesquisador> insert(@RequestBody Pesquisador objeto) {
        Pesquisador registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar um pesquisador existente",
        description = "Atualiza as informações de um pesquisador existente"
    )
    public ResponseEntity<Pesquisador> update(@RequestBody Pesquisador objeto) {
        Pesquisador registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar um pesquisador",
        description = "Deleta o pesquisador com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @PostMapping("/teste")
    @Operation(
        summary = "Cadastrar um novo pesquisador",
        description = "Cadastra um novo pesquisador"
    )
    public ResponseEntity<PesquisadorEstrangeiroDto> insertEstrangeiro(@RequestBody @Valid PesquisadorEstrangeiroDto objeto) {
        Pesquisador objetoConvertido = mapper.toEntity(objeto);
        Pesquisador registro = servico.save(objetoConvertido);
        PesquisadorEstrangeiroDto dto = mapper.toDto(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    

}