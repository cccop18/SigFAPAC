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

import fapacapi.controller.dto.VinculoInstitucionalDto;
import fapacapi.controller.mapper.VinculoInstitucionalMapper;
import fapacapi.model.VinculoInstitucional;
import fapacapi.service.VinculoInstitucionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/vinculosInstitucionais", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "VinculoInstitucional",
    description = "Endpoints para gerenciar os vinculos instituicionais"
)
public class VinculoInstitucionalController implements IController<VinculoInstitucional> {

    private final VinculoInstitucionalService servico;
    private final VinculoInstitucionalMapper mapper;

    public VinculoInstitucionalController(VinculoInstitucionalService servico, VinculoInstitucionalMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;   
    }

    @Override
    @GetMapping("/")
    @Operation(
        summary = "Obtém todos os vinculos institucionais ou filtrado por termo de busca",
        description = "Obtém uma lista paginada de todos os vinculos institucionais cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<VinculoInstitucional>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "vinculoInstitucional", direction = Sort.Direction.ASC)
        })
        @ParameterObject Pageable page) {
        Page<VinculoInstitucional> registros = servico.get(termoBusca, page); 
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vinculo institucional encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "Vinculo institucional não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obtém o vinculo institucional por ID",
        description = "Obtém o vinculo institucional com o ID informado"
    )
    public ResponseEntity<VinculoInstitucional> get(@PathVariable("id") Long id) {
        VinculoInstitucional registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }
    @PostMapping("/")
    @Operation(
        summary = "Cadastrar um vinculo institucional para o pesquisador",
        description = "Cadastra um novo vinculo institucional para o pesquisador"
    )
    public ResponseEntity<VinculoInstitucionalDto> insert(@RequestBody @Valid VinculoInstitucionalDto objeto) {
        VinculoInstitucional objetoConvertido = mapper.toEntity(objeto);
        VinculoInstitucional registro = servico.save(objetoConvertido);
        VinculoInstitucionalDto dto = mapper.toDto(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    @PutMapping("/")
    @Operation(
        summary = "Atualizar um vinculo institucional do pesquisador",
        description = "Atualiza um vinculo institucional existente do pesquisador"
    )
    public ResponseEntity<VinculoInstitucionalDto> update(@RequestBody @Valid VinculoInstitucionalDto objeto) {
        VinculoInstitucional objetoConvertido = mapper.toEntity(objeto);
        VinculoInstitucional registro = servico.save(objetoConvertido);
        VinculoInstitucionalDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar vinculo institucional do pesquisador",
        description = "Deleta o vinculo institucional do pesquisador com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    public ResponseEntity<?> insert(VinculoInstitucional objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public ResponseEntity<?> update(VinculoInstitucional objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}