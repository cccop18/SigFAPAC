package fapacapi.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fapacapi.controller.dto.PesquisadorBrasileiroDto;
import fapacapi.controller.dto.PesquisadorConhecimentoDto;
import fapacapi.controller.dto.VinculoInstitucionalDto;
import fapacapi.controller.mapper.PesquisadorBrasileiroMapper;
import fapacapi.model.AreaConhecimento;
import fapacapi.model.EnderecoPesquisador;
import fapacapi.model.Instituicao;
import fapacapi.model.Pesquisador;
import fapacapi.model.PesquisadorConhecimento;
import fapacapi.model.PrimeiraSubArea;
import fapacapi.model.SegundaSubArea;
import fapacapi.model.TerceiraSubArea;
import fapacapi.model.TipoPesquisador;
import fapacapi.model.UnidadeInstituicao;
import fapacapi.model.VinculoInstitucional;
import fapacapi.service.AreaConhecimentoService;
import fapacapi.service.EnderecoPesquisadorService;
import fapacapi.service.InstituicaoService;
import fapacapi.service.PesquisadorConhecimentoService;
import fapacapi.service.PesquisadorService;
import fapacapi.service.PrimeiraSubAreaService;
import fapacapi.service.SegundaSubAreaService;
import fapacapi.service.TerceiraSubAreaService;
import fapacapi.service.UnidadeInstituicaoService;
import fapacapi.service.VinculoInstitucionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/pesquisadorBr", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Pesquisador Brasileiro",
    description = "Endpoints para gerenciar os pesquisadores brasileiros"
)
public class PesquisadorBrasileiroController {

    private static final String caminhoImagem = "uploads/imgs";

    private final PesquisadorService servico;
    private final PesquisadorBrasileiroMapper mapper;
    private final EnderecoPesquisadorService end;
    private final VinculoInstitucionalService vinculoInstitucionalService;
    private final PesquisadorConhecimentoService pesquisadorConhecimento;
    private final InstituicaoService instituicaoService;
    private final UnidadeInstituicaoService unidadeInstituicaoService;
    private final AreaConhecimentoService areaConhecimentoService;
    private final PrimeiraSubAreaService primeiraSubAreaService;
    private final SegundaSubAreaService segundaSubAreaService;
    private final TerceiraSubAreaService terceiraSubAreaService;

    public PesquisadorBrasileiroController(
        PesquisadorService servico,
        PesquisadorBrasileiroMapper mapper,
        EnderecoPesquisadorService end, 
            VinculoInstitucionalService vinculoInstitucionalService, 
            PesquisadorConhecimentoService areaConhecimento, 
            InstituicaoService instituicaoService,
            AreaConhecimentoService areaConhecimentoService,
            PrimeiraSubAreaService primeiraSubAreaService,
            SegundaSubAreaService segundaSubAreaService,
            TerceiraSubAreaService terceiraSubAreaService,
            UnidadeInstituicaoService unidadeInstituicaoService) {
            
        this.servico = servico;
        this.mapper = mapper;
        this.end = end;
        this.vinculoInstitucionalService = vinculoInstitucionalService;
        this.pesquisadorConhecimento = areaConhecimento;
        this.instituicaoService = instituicaoService;
        this.areaConhecimentoService = areaConhecimentoService;
        this.primeiraSubAreaService = primeiraSubAreaService;
        this.segundaSubAreaService = segundaSubAreaService;
        this.terceiraSubAreaService = terceiraSubAreaService;
        this.unidadeInstituicaoService = unidadeInstituicaoService;
    }

     @GetMapping("/")
    @Operation(
        summary = "Obtém todos os pesquisadores Estrangeiros ou filtrado por termo de busca",
        description = "Obtém uma lista paginada de todos os pesquisadores estrangeiros cadastrados ou que contenham o termo de busca informado"
    )
    public ResponseEntity<Page<PesquisadorBrasileiroDto>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "nomeCompletoPesquisador", direction = Sort.Direction.ASC)
        })
        @ParameterObject Pageable page) {
        Page<Pesquisador> registros = servico.get(termoBusca, page);
        Page<PesquisadorBrasileiroDto> dtos = registros.map(mapper::toDto); 
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pesquisador brasileiro encontrado"),
        @ApiResponse(
            responseCode = "404",
            description = "Pesquisador brasileiro não encontrado",
            content = @Content(
                examples = @ExampleObject("")
            )
        )
    })
    @Operation(
        summary = "Obtém o pesquisador estrangeiro por ID",
        description = "Obtém o pesquisador estrangeiro com o ID informado"
    )
    public ResponseEntity<PesquisadorBrasileiroDto> get(@PathVariable("id") Long id) {
        Pesquisador registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        PesquisadorBrasileiroDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

     @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
        summary = "Cadastrar um novo pesquisador estrangeiro",
        description = "Cadastra um novo pesquisador estrangeiro"
    )
    public ResponseEntity<PesquisadorBrasileiroDto> insert(@RequestPart @Valid PesquisadorBrasileiroDto objeto, 
        @RequestPart("fileFoto") MultipartFile fotoPesquisador) {
        Pesquisador pesquisadorSalvo = inserirPesquisadorEstrangeiro(objeto, fotoPesquisador);
        // Convertendo para DTO e retornando a resposta
        PesquisadorBrasileiroDto dto = mapper.toDto(pesquisadorSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Transactional
    private Pesquisador inserirPesquisadorEstrangeiro(PesquisadorBrasileiroDto objeto, MultipartFile fotoPesquisador) {
        Pesquisador pesquisador = new Pesquisador();
        TipoPesquisador tipoPesquisador = new TipoPesquisador();
        EnderecoPesquisador endereco = new EnderecoPesquisador();

        tipoPesquisador.setIdTipoPesquisador(objeto.idTipoPesquisador());

        
        pesquisador.setNomeCompletoPesquisador(objeto.nomeCompletoPesquisador());
        pesquisador.setEmailPesquisador(objeto.emailPesquisador());
        pesquisador.setSexoPesquisador(objeto.sexoPesquisador());
        //Conversão da data de nascimento para salvar 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataNascimento = LocalDate.parse(objeto.dataNascimentoPesquisador(), formatter);
        pesquisador.setDataNascimentoPesquisador(dataNascimento);

        pesquisador.setCorRaca(objeto.corRaca());
        pesquisador.setNomeMaePesquisador(objeto.nomeMaePesquisador());
        pesquisador.setNomePaiPesquisador(objeto.nomePaiPesquisador()); 
        pesquisador.setCurriculoLattesPesquisador(objeto.curriculoLattesPesquisador());
        pesquisador.setNivelAcademicoPesquisador(objeto.nivelAcademicoPesquisador());
        pesquisador.setRgPesquisador(objeto.rgPesquisador());

        // Campos diferentes para pesquisador Brasileiro
        pesquisador.setCpfPesquisador(objeto.cpfPesquisador());
        pesquisador.setOrgaoEmissorPesquisador(objeto.orgaoEmissorPesquisador());
        pesquisador.setUfPesquisador(objeto.ufPesquisador());
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataEmissaoRg = LocalDate.parse(objeto.dataEmissaoPesquisador(), formatar);
        pesquisador.setDataEmissaoPesquisador(dataEmissaoRg);


        pesquisador.setTelefonePesquisador(objeto.telefonePesquisador());
        pesquisador.setSenhaPesquisador(objeto.senhaPesquisador());
        pesquisador.setTipoPesquisador(tipoPesquisador);

        // Salvando o pesquisador estrangeiro
        Pesquisador pesquisadorSalvo = servico.save(pesquisador);

        // Salvando a foto do pesquisador
        try {
            if (!fotoPesquisador.isEmpty()) {
                // Verifica se o diretório existe e, se não existir, cria
                Path diretorio = Paths.get(caminhoImagem);
                if (!Files.exists(diretorio)) {
                    Files.createDirectories(diretorio);
                }

                // Cria o caminho completo para o arquivo
                Path caminho = Paths.get(caminhoImagem,
                        String.valueOf(pesquisadorSalvo.getIdPesquisador()) + " " + fotoPesquisador.getOriginalFilename());

                // Salva o arquivo na localização especificada
                Files.write(caminho, fotoPesquisador.getBytes());

                // Define o nome do arquivo salvo no objeto Arquivo
                pesquisadorSalvo.setFotoPesquisador(String.valueOf(pesquisadorSalvo.getIdPesquisador()) + " " + fotoPesquisador.getOriginalFilename());

                // Salvar novamente o objeto com o nome do arquivo atualizado
                servico.save(pesquisadorSalvo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Set nos valores do endereço residencial
        endereco.setTipoEnderecoPesquisador(objeto.tipoEnderecoResidencial());
        endereco.setCepEnderecoPesquisador(objeto.cepEnderecoResidencial());
        endereco.setLogradouroEnderecoPesquisador(objeto.logradouroEnderecoResidencial());
        endereco.setNumeroEnderecoPesquisador(objeto.numeroEnderecoResidencial());
        endereco.setBairroEnderecoPesquisador(objeto.bairroEnderecoResidencial());
        endereco.setPaisEnderecoPesquisador(objeto.paisEnderecoResidencial());
        endereco.setEstadoEnderecoPesquisador(objeto.estadoEnderecoResidencial());
        endereco.setPesquisador(pesquisadorSalvo);
        end.save(endereco);

        // Salvando Endereço Profissional (se existir)
        if (objeto.cepEnderecoProfissional() != null) {
            EnderecoPesquisador enderecoProfissional = new EnderecoPesquisador();
            enderecoProfissional.setTipoEnderecoPesquisador(objeto.tipoEnderecoProfissional());
            enderecoProfissional.setCepEnderecoPesquisador(objeto.cepEnderecoProfissional());
            enderecoProfissional.setLogradouroEnderecoPesquisador(objeto.logradouroEnderecoProfissional());
            enderecoProfissional.setNumeroEnderecoPesquisador(objeto.numeroEnderecoProfissional());
            enderecoProfissional.setBairroEnderecoPesquisador(objeto.bairroEnderecoProfissional());
            enderecoProfissional.setPaisEnderecoPesquisador(objeto.paisEnderecoProfissional());
            enderecoProfissional.setEstadoEnderecoPesquisador(objeto.estadoEnderecoProfissional());
            enderecoProfissional.setPesquisador(pesquisadorSalvo);
            end.save(enderecoProfissional);
        }

        // Salvando Vinculos Institucionais
        // Mapeando e salvando vínculos institucionais a partir do DTO
    for (VinculoInstitucionalDto vinculoDto : objeto.vinculosInstitucionais()) {
        VinculoInstitucional vinculo = new VinculoInstitucional();
        
        // Verificar se a instituição e unidade possuem IDs válidos
        Instituicao instituicaoExistente = instituicaoService.FindById(vinculoDto.idInstituicao());
        UnidadeInstituicao unidadeExistente = unidadeInstituicaoService.FindById(vinculoDto.idUnidadeInstituicao());

        if (instituicaoExistente == null) {
            throw new EntityNotFoundException("Instituição com ID " + vinculoDto.idInstituicao() + " não encontrada");
        }

        if (unidadeExistente == null) {
            throw new EntityNotFoundException("Unidade Institucional com ID " + vinculoDto.idUnidadeInstituicao() + " não encontrada");
        }

        vinculo.setInstituicao(instituicaoExistente);
        vinculo.setUnidadeInstituicao(unidadeExistente);
        vinculo.setPesquisador(pesquisadorSalvo);
        vinculo.setVinculoInstitucional(vinculoDto.vinculoInstitucional());
        vinculo.setVinculoEmpregaticio(vinculoDto.vinculoEmpregaticio());
        vinculo.setTempoServico(vinculoDto.tempoServico());
        vinculo.setRegimeTrabalho(vinculoDto.regimeTrabalho());
        vinculo.setFuncaoCargo(vinculoDto.funcaoCargo());
        vinculo.setTempoDaFuncao(vinculoDto.tempoDaFuncao());

        // Salvar o vínculo institucional
        vinculoInstitucionalService.save(vinculo);
    }
    // Salvando os conhecimentos do pesquisador
    for (PesquisadorConhecimentoDto conhecimentoDto : objeto.pesquisadorConhecimento()) {
        PesquisadorConhecimento conhecimento = new PesquisadorConhecimento();

        // Associação da área de conhecimento usando o ID fornecido no DTO
        AreaConhecimento areaConhecimento = areaConhecimentoService.findById(conhecimentoDto.idAreaConhecimento());
        conhecimento.setAreaConhecimento(areaConhecimento);

        // Associação das subáreas (se existirem)
        if (conhecimentoDto.idPrimeiraSubArea() != null) {
            PrimeiraSubArea primeiraSubArea = primeiraSubAreaService.findById(conhecimentoDto.idPrimeiraSubArea());
            conhecimento.setPrimeiraSubArea(primeiraSubArea);
        }

        if (conhecimentoDto.idSegundaSubArea() != null && conhecimentoDto.idSegundaSubArea() > 0) {
            SegundaSubArea segundaSubArea = segundaSubAreaService.findById(conhecimentoDto.idSegundaSubArea());
            conhecimento.setSegundaSubArea(segundaSubArea);
        }

        if (conhecimentoDto.idTerceiraSubArea() != null && conhecimentoDto.idTerceiraSubArea() > 0) {
            TerceiraSubArea terceiraSubArea = terceiraSubAreaService.findById(conhecimentoDto.idTerceiraSubArea());
            conhecimento.setTerceiraSubArea(terceiraSubArea);
        }

        // Associar o pesquisador ao conhecimento e salvar
        conhecimento.setPesquisador(pesquisadorSalvo);
        pesquisadorConhecimento.save(conhecimento);
    }
        return pesquisadorSalvo;
    }
    @PutMapping("/")
    @Operation(
        summary = "Atualizar um pesquisador brasileiro existente",
        description = "Atualiza as informações de um pesquisador brasileiro existente"
    )
    public ResponseEntity<PesquisadorBrasileiroDto> update(@RequestBody @Valid PesquisadorBrasileiroDto objeto) {
        Pesquisador objetoConvertido = mapper.toEntity(objeto);
        Pesquisador registro = servico.save(objetoConvertido);
        PesquisadorBrasileiroDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar um pesquisador brasileiro",
        description = "Deleta o pesquisador brasileiro com o ID informado"
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}   
