package fapacapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fapacapi.controller.dto.PropostaTituloDto;
import fapacapi.controller.mapper.PropostaTituloMapper;
import fapacapi.model.Proposta;
import fapacapi.service.AreaConhecimentoService;
import fapacapi.service.EditalService;
import fapacapi.service.InstituicaoService;
import fapacapi.service.PrimeiraSubAreaService;
import fapacapi.service.PropostaConhecimentoService;
import fapacapi.service.PropostaService;
import fapacapi.service.SegundaSubAreaService;
import fapacapi.service.TerceiraSubAreaService;
import fapacapi.service.UnidadeInstituicaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/propostastitulo", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "propostaTitulo", description = "Endpoints para cadastro de propostas de títulos")
public class PropostaTituloController {

    private final PropostaService servico;
    private final PropostaTituloMapper mapper;
    

    public PropostaTituloController(PropostaService servico,
            PropostaTituloMapper mapper,
            PropostaConhecimentoService conhecimentoService,
            InstituicaoService instituicaoService,
            AreaConhecimentoService areaConhecimentoService,
            PrimeiraSubAreaService primeiraSubAreaService,
            SegundaSubAreaService segundaSubAreaService,
            TerceiraSubAreaService terceiraSubAreaService,
            UnidadeInstituicaoService unidadeInstituicaoService,
            EditalService editalService) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do edital não encontrado"),
            @ApiResponse(responseCode = "404", description = "Dados do edital não encontrado", content = @Content(examples = @ExampleObject("")))
    })
    @Operation(summary = "Obeter um editalDados por ID", description = "Obtém um editalDados com o ID infromado")
    public ResponseEntity<PropostaTituloDto> get(@PathVariable("id") Long id) {
        Proposta registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        PropostaTituloDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/")
    @Operation(summary = "Cadastrar um Titulo da Proposta", description = "Cadastra um novo Titulo para Proposta")
    public ResponseEntity<PropostaTituloDto> insert(@RequestBody @Valid PropostaTituloDto objeto) {
        PropostaTituloDto dto = servico.criarProposta(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
