package fapacapi.controller;

import java.util.List;

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

import fapacapi.model.AreaConhecimento;
import fapacapi.model.PrimeiraSubArea;
import fapacapi.model.SegundaSubArea;
import fapacapi.model.TerceiraSubArea;
import fapacapi.service.AreaConhecimentoService;
import fapacapi.controller.dto.SubareaDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/areaConhecimento", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Área de Conhecimento",
    description = "Endpoints para gerenciar a Área de Conhecimento do pesquisador"
)
public class AreaConhecimentoController implements IController<AreaConhecimento> {

    private final AreaConhecimentoService servico;

    public AreaConhecimentoController(AreaConhecimentoService servico) {
        this.servico = servico;
    }

    // Criar uma nova área de conhecimento
    @PostMapping
    public AreaConhecimento criarArea(@RequestParam String nomeAreaConhecimento) {
        return servico.criarArea(nomeAreaConhecimento);
    }

    // Adicionar subárea (Primeira, Segunda ou Terceira)
    @PostMapping("/{id}/subarea")
    public ResponseEntity<?> adicionarSubarea(@PathVariable Long id, @RequestBody SubareaDto subareaDto) {
        AreaConhecimento area = servico.findById(id);
        if (area == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Área de conhecimento não encontrada.");
        }

        boolean sucesso = servico.adicionarSubarea(area, subareaDto);
        if (sucesso) {
            return ResponseEntity.ok("Subárea adicionada com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao adicionar subárea.");
        }

    }

    // Buscar primeira subárea por área de conhecimento
    @GetMapping("/{id}/primeiraSubArea")
    public ResponseEntity<List<PrimeiraSubArea>> getPrimeiraSubAreas(@PathVariable Long id) {
        List<PrimeiraSubArea> subAreas = servico.getPrimeiraSubAreasByArea(id);
        return ResponseEntity.ok(subAreas);
    }

    // Buscar segunda subárea por primeira subárea
    @GetMapping("/primeiraSubArea/{id}/segundaSubArea")
    public ResponseEntity<List<SegundaSubArea>> getSegundaSubAreas(@PathVariable Long id) {
        List<SegundaSubArea> subAreas = servico.getSegundaSubAreasByPrimeiraSubArea(id);
        return ResponseEntity.ok(subAreas);
    }

    // Buscar terceira subárea por segunda subárea
    @GetMapping("/segundaSubArea/{id}/terceiraSubArea")
    public ResponseEntity<List<TerceiraSubArea>> getTerceiraSubAreas(@PathVariable Long id) {
        List<TerceiraSubArea> subAreas = servico.getTerceiraSubAreasBySegundaSubArea(id);
        return ResponseEntity.ok(subAreas);
    }

    // Obter todas as áreas de conhecimento ou filtrar por termo de busca
    @Override
    @GetMapping("/")
    @Operation(
        summary = "Obtém todas as Áreas de Conhecimento ou filtra por termo de busca",
        description = "Obtém uma lista paginada de todas as Áreas de Conhecimento dos pesquisadores ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<AreaConhecimento>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "nomeAreaConhecimento", direction = Sort.Direction.ASC)
        })
        @ParameterObject Pageable page) {
        Page<AreaConhecimento> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
    }

    // Obter área de conhecimento por ID
    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Área de Conhecimento"),
        @ApiResponse(
            responseCode = "404",
            description = "Área de Conhecimento não encontrada",
            content = @Content(examples = @ExampleObject(""))
        )
    })
    @Operation(
        summary = "Obtém uma Área de Conhecimento por ID",
        description = "Obtém a Área de Conhecimento com o ID informado"
    )
    public ResponseEntity<AreaConhecimento> get(@PathVariable("id") Long id) {
        AreaConhecimento registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    // Escolher uma Área de Conhecimento
    @Override
    @PostMapping("/")
    @Operation(
        summary = "Escolher uma Área de Conhecimento para o pesquisador",
        description = "Escolher uma Área de Conhecimento"
    )
    public ResponseEntity<AreaConhecimento> insert(@RequestBody AreaConhecimento objeto) {
        AreaConhecimento registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    // Atualizar uma Área de Conhecimento
    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar uma Área de Conhecimento",
        description = "Atualiza uma Área de Conhecimento existente"
    )
    public ResponseEntity<AreaConhecimento> update(@RequestBody AreaConhecimento objeto) {
        AreaConhecimento registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    // Deletar uma Área de Conhecimento por ID
    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar uma Área de Conhecimento",
        description = "Deleta a Área de Conhecimento com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}