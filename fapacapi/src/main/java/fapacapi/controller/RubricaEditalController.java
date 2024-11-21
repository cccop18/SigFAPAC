package fapacapi.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.data.domain.Sort;
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

import fapacapi.model.RubricaEdital;
import fapacapi.service.RubricaEditalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/rubricaEdital", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "rubricaEdital",
    description = "Endipoints para gerenciar rubricaEdital"
)
public class RubricaEditalController implements IController<RubricaEdital>{

    private final RubricaEditalService servico;

    public RubricaEditalController(RubricaEditalService servico) {
        this.servico = servico;
    }

	@Override
    @GetMapping("/")
    @Operation(
        summary = "Obtém todas as RubricaEdital ou filtra por termo busca",
        description = "Obtém uma lista paginada de todas as RubricaEdital que contenham o termo de busca informado"
    )
	public ResponseEntity<?> get(@RequestParam(required = false) String termoBusca, 
        @RequestParam(required = false) boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "rubricas", direction = Sort.Direction.ASC)
        })
        @ParameterObject Pageable page) {
        Page<RubricaEdital> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
		
	}

	@Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "RubricaEdital"),
        @ApiResponse(
            responseCode = "404",
            description = "RubricaEdital não encontrada",
            content = @Content(examples = @ExampleObject(""))
        )
    })
    @Operation(
        summary = "Obtém uma RubricaEdital por ID",
        description = "Obtém a RubricaEdital com o ID informado"
    )
	public ResponseEntity<RubricaEdital> get(@PathVariable("id") Long id) {
		RubricaEdital registro = servico.get(id);
        if(registro == null){
            return ResponseEntity.status(HttpStatus.NOT_EXTENDED).body(null);
        }
        return ResponseEntity.ok(registro);
	}

	@Override
    @PostMapping("/")
    @Operation(
        summary = "Escolher uma RubricaEdital",
        description = "Escolher uma RubricaEdital"
    )
	public ResponseEntity<RubricaEdital> insert(@RequestBody RubricaEdital objeto) {
		RubricaEdital registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}

	@Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar uma RubricaEdital",
        description = "Atualizar uma RubricaEdital já existente"
    )
	public ResponseEntity<RubricaEdital> update(@RequestBody RubricaEdital objeto) {
		RubricaEdital registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
	}

	@Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar uma RubricaEdital",
        description = "Deleta uma RubricaEdital com o ID informado"
    )
	public ResponseEntity<?> delete(@PathVariable Long id) {
		servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
	}
    
}
