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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fapacapi.controller.dto.UnidadeInstituicaoDto;
import fapacapi.controller.dto.UnidadeNomeDto;
import fapacapi.controller.mapper.UnidadeInstituicaoMapper;
import fapacapi.model.EnderecoUnidade;
import fapacapi.model.Instituicao;
import fapacapi.model.UnidadeInstituicao;
import fapacapi.service.EnderecoUnidadeService;
import fapacapi.service.UnidadeInstituicaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/unidadesInstituicao", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Unidades",
    description = "Endpoints para gerenciar unidades"
)
public class UnidadeInstituicaoController implements IController<UnidadeInstituicao>{

    private final UnidadeInstituicaoService servico;
    private final UnidadeInstituicaoMapper mapper;
    private final EnderecoUnidadeService end;

    public UnidadeInstituicaoController(UnidadeInstituicaoService servico, UnidadeInstituicaoMapper mapper, EnderecoUnidadeService end) {
        this.servico = servico;
        this.mapper = mapper;
        this.end = end;
    }

    @Override
    @GetMapping("/")
    @Operation(
        summary = "Obeter Unidades ou filtrar por termo busca",
        description = "Obtém todas as unidades"
    )
    public ResponseEntity<Page<UnidadeInstituicao>> get(
            @RequestParam(required = false) String termoBusca, 
            @RequestParam(required = false, defaultValue = "false") boolean unpaged, 
            @SortDefault.SortDefaults({
                @SortDefault(sort = "nome", direction = Sort.Direction.ASC)
            }) 
            @ParameterObject Pageable page) {
        if (unpaged){
            page = Pageable.unpaged();
        }
        Page<UnidadeInstituicao> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
        
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Unidade encontrada"),
        @ApiResponse(
            responseCode = "404",
            description = "Unidade não encontrada",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obeter uma Unidade por ID",
        description = "Obtém a unidade com o ID informado"
    )
    public ResponseEntity<UnidadeInstituicao> get(@PathVariable("id")Long id) {
        UnidadeInstituicao registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @PostMapping("/")
    @Operation(
        summary = "Cadastrar unidade",
        description = "Cadastra uma nova unidade"
    )
    public ResponseEntity<UnidadeInstituicaoDto> insert(@RequestBody @Valid UnidadeInstituicaoDto objeto) {
        UnidadeInstituicao registro = inserirunidade(objeto);

        // Convertendo para DTO e retornando a resposta
        UnidadeInstituicaoDto dto = mapper.toDto(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Transactional
    private UnidadeInstituicao inserirunidade(UnidadeInstituicaoDto objeto) {
        //Separando para poder salvar os dados em tabelas diferentes
        UnidadeInstituicao unidade = new UnidadeInstituicao();
        EnderecoUnidade endereco = new EnderecoUnidade();
        Instituicao instituicao = new Instituicao();

        instituicao.setIdInstituicao(objeto.idInstituicao());

        endereco.setCepEnderecoUnidade(objeto.cepEnderecoUnidade());
        endereco.setBairroEnderecoUnidade(objeto.bairroEnderecoUnidade());
        endereco.setEstadoEnderecoUnidade(objeto.estadoEnderecoUnidade());
        endereco.setLogradouroEnderecoUnidade(objeto.logradouroEnderecoUnidade());
        endereco.setNumeroEnderecoUnidade(objeto.numeroEnderecoUnidade());
        endereco.setPaisEnderecoUnidade(objeto.paisEnderecoUnidade());

         // Salvando o endereço e obtendo o ID gerado
        EnderecoUnidade enderecoSalvo = end.save(endereco);

        // Populando os dados da unidade e associando o endereço salvo
        unidade.setNomeUnidade(objeto.nomeUnidade());
        unidade.setEmailUnidade(objeto.emailUnidade());
        unidade.setTelefoneUnidade(objeto.telefoneUnidade());
        unidade.setInstituicao(instituicao);
        unidade.setEnderecoUnidade(enderecoSalvo); // Associando o endereço salvo à unidade

        UnidadeInstituicao registro = servico.save(unidade);
        return registro;
    }

    @Override
    @PutMapping("/")
    @Operation(
        summary = "Atualizar unidade",
        description = "Atualiza uma unidade existente"
    )
    public ResponseEntity<UnidadeInstituicao> update(@RequestBody UnidadeInstituicao objeto) {
        UnidadeInstituicao registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar unidade",
        description = "Deleta uma unidade com o id informado"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    public ResponseEntity<?> insert(UnidadeInstituicao objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }
    @GetMapping("/instituicao/{instituicaoId}")
    public ResponseEntity<List<UnidadeNomeDto>> getUnidadesByInstituicao(@PathVariable Long instituicaoId) {
    List<UnidadeInstituicao> unidades = servico.getUnidadesByInstituicao(instituicaoId);
    
    // Converta a lista de UnidadeInstituicao para UnidadeNomeDto
    List<UnidadeNomeDto> unidadeDtos = unidades.stream()
            .map(unidade -> new UnidadeNomeDto(unidade.getNomeUnidade(), unidade.getIdUnidadeInstituicao())) // Mapeando apenas o nome
            .toList();
    
    return ResponseEntity.ok(unidadeDtos);

}
    
}