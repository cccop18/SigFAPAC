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

import fapacapi.controller.mapper.EditalDadosMapper;
import fapacapi.model.Edital;
import fapacapi.service.EditalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping( value = "/edital", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Edital",
    description = "Endpoints para Editais"
)
public class EditalController implements IController<Edital>{

    // Variáveis de instância do caminho da arquivo em pdf
    private static final String caminhoFile = "uploads/Editais";
    
    private final EditalService servico;
    private final EditalDadosMapper mapper;


    public EditalController(EditalService servico, EditalDadosMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @Override
    @GetMapping("/")
    @Operation(
        summary = "Obtém os editais ou filtrar por termo de busca",
        description = "Obtém uma lista paginada de todos os editais cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<Edital>> get(
        @RequestParam(required = false) String termoBusca,  
        @RequestParam(required = false, defaultValue = "false")boolean unpaged, 
        @SortDefault.SortDefaults({
            @SortDefault(sort = "tituloEdital", direction = Sort.Direction.ASC)
        })
       @ParameterObject Pageable page) {
        if (unpaged) {
            page = Pageable.unpaged();
        }
       Page<Edital> registros = servico.get(termoBusca, page);
       return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Editais encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "Editais não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obter editais por ID",
        description = "Obtém editais com o ID informado"
    )
    public ResponseEntity<Edital> get(@PathVariable("id") Long id) {
        Edital registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar editais",
        description = "Cadastra um novo edital"
    )
    public ResponseEntity<Edital> insert(@RequestBody Edital objeto) {
        Edital registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar edital",
        description = "Atualiza um edital existente"
    )
    public ResponseEntity<Edital> update(@RequestParam Edital objeto) {
        Edital registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar edital",
        description = "Deleta o edital com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}