package fapacapi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fapacapi.controller.dto.EditalChamadasDto;
import fapacapi.controller.dto.EditalDadosDto;
import fapacapi.controller.dto.EditalOrcamentoDto;
import fapacapi.controller.dto.EditalPlanoApresentacaoDto;
import fapacapi.controller.dto.EditalRestricoesDto;
import fapacapi.controller.dto.RubricaSelecionadaDto;
import fapacapi.model.Edital;
import fapacapi.model.Moeda;
import fapacapi.model.Programa;
import fapacapi.model.Rubrica;
import fapacapi.model.RubricaEdital;
import fapacapi.repository.EditalRepository;
import fapacapi.repository.MoedaRepository;
import fapacapi.repository.ProgramaRepository;
import fapacapi.repository.RubricaEditalRepository;
import fapacapi.repository.RubricaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EditalService implements IService<Edital> {

    private final EditalRepository repo;
    private final MoedaRepository moedaRepository;
    private final ProgramaRepository programaRepository;
    private final RubricaEditalRepository rubricaEditalRepository;
    private final RubricaRepository rubricaRepository;
    private static final String caminhoFile = "uploads/Editais";

    public EditalService(EditalRepository repo, ProgramaRepository programaRepository, MoedaRepository moedaRepository, RubricaEditalRepository rubricaEditalRepository, RubricaRepository rubricaRepository) {
        this.repo = repo;
        this.programaRepository = programaRepository;
        this.moedaRepository = moedaRepository;
        this.rubricaRepository = rubricaRepository;
        this.rubricaEditalRepository = rubricaEditalRepository;
    }

    @Override
    @Cacheable(value = "editais", condition = "#termoBusca == null or #termoBusca.isBlank()")
    public Page<Edital> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()) {
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "edital", unless = "#result == null")
    public Edital get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "edital", key = "#objeto.id"),
            @CacheEvict(value = "editais", allEntries = true)
    })
    public Edital save(Edital objeto) {
        if (objeto.getDataCriacaoEdital() == null || objeto.getDataCriacaoEdital().isBefore(LocalDateTime.now())) {
            objeto.setDataCriacaoEdital(LocalDateTime.now());
        }
        if (objeto.getSituacaoEdital() == null || objeto.getSituacaoEdital().isBlank()) {
            objeto.setSituacaoEdital("Em edição");
        }
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "edital", key = "#objeto.id"),
            @CacheEvict(value = "editais", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // Esse método apaga todos os valores da chamada do edital
    public Edital deleteChamadas(Long id) {
        Edital registro = this.get(id);

        // Zera ou define como nulos os campos desejados
        registro.setDataAberturaEdital(null);
        registro.setHoraAberturaEdital(null);
        registro.setDataFechamentoEdital(null);
        registro.setHoraFechamentoEdital(null);
        registro.setDataEnquadramentoEdital(null);
        registro.setDataRecursoEdital(null);
        registro.setDataResultadoEdital(null);
        registro.setDataRecursoFinalEdital(null);
        registro.setDataBolsaEdital(null);
        registro.setInformacaoChamadasEdital(null);

        // Salva o registro atualizado no banco de dados
        this.save(registro);
        return registro;
    }

    public Edital updateOrcamento(EditalOrcamentoDto dto) {
        Edital registroExistente = repo.findById(dto.idEdital())
            .orElseThrow(() -> new EntityNotFoundException("Edital não encontrado para o ID fornecido."));

        // Atualizações dos limites anuais
        if (dto.limiteAnualPrimeiroAno() != null) {
            registroExistente.setLimiteAnualPrimeiroAno(dto.limiteAnualPrimeiroAno());
        }
        if (dto.limiteAnualSegundoAno() != null) {
            registroExistente.setLimiteAnualSegundoAno(dto.limiteAnualSegundoAno());
        }
        if (dto.limiteAnualTerceiroAno() != null) {
            registroExistente.setLimiteAnualTerceiroAno(dto.limiteAnualTerceiroAno());
        }
        if (dto.limiteAnualQuartoAno() != null) {
            registroExistente.setLimiteAnualQuartoAno(dto.limiteAnualQuartoAno());
        }
        if (dto.limiteAnualQuintoAno() != null) {
            registroExistente.setLimiteAnualQuintoAno(dto.limiteAnualQuintoAno());
        }
        registroExistente.setCordenadorConfigOrcamentoEdital(dto.cordenadorConfigOrcamentoEdital());

        // Configuração da moeda
        Moeda moeda;
        if (dto.moeda() != null) {
            moeda = moedaRepository.findById(dto.moeda())
                .orElseThrow(() -> new EntityNotFoundException("Moeda não encontrada para o ID fornecido."));
        } else {
            moeda = moedaRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("Moeda Real Brasileiro não encontrada."));
        }
        registroExistente.setMoeda(moeda);

        // Processamento das rubricas selecionadas
        if (dto.rubricasSelecionadas() != null && !dto.rubricasSelecionadas().isEmpty()) {
            for (RubricaSelecionadaDto rubricaSelecionada : dto.rubricasSelecionadas()) {
                Rubrica rubrica = rubricaRepository.findById(rubricaSelecionada.idRubrica())
                    .orElseThrow(() -> new EntityNotFoundException("Rubrica não encontrada para o ID: " + rubricaSelecionada.idRubrica()));
    
                RubricaEdital rubricaEdital = new RubricaEdital();
                rubricaEdital.setRubrica(rubrica);
                rubricaEdital.setEdital(registroExistente);
    
                // Verifica se a porcentagem é nula, e se for, usa 0 como valor padrão
                rubricaEdital.setPorcentagemRubricaEdital(
                    rubricaSelecionada.porcentagemRubricaEdital() != null ? rubricaSelecionada.porcentagemRubricaEdital() : 0
                );
    
                rubricaEditalRepository.save(rubricaEdital);  // Salva a RubricaEdital
            }
        }
    
        return repo.save(registroExistente);
    }


    public Edital updateChamadas(EditalChamadasDto dto) {
        // Busca a entidade existente no banco de dados usando o ID fornecido no DTO
        Edital registroExistente = repo.findById(dto.idEdital())
                .orElseThrow(() -> new EntityNotFoundException("Edital não encontrado para o ID fornecido."));

        // Atualiza apenas os campos específicos do DTO
        if (dto.dataAberturaEdital() != null) {
            registroExistente.setDataAberturaEdital(dto.dataAberturaEdital());
        }
        if (dto.horaAberturaEdital() != null) {
            registroExistente.setHoraAberturaEdital(LocalTime.parse(dto.horaAberturaEdital()));
        }
        if (dto.dataFechamentoEdital() != null) {
            registroExistente.setDataFechamentoEdital(dto.dataFechamentoEdital());
        }
        if (dto.horaFechamentoEdital() != null) {
            registroExistente.setHoraFechamentoEdital(LocalTime.parse(dto.horaFechamentoEdital()));
        }
        if (dto.dataEnquadramentoEdital() != null) {
            registroExistente.setDataEnquadramentoEdital(dto.dataEnquadramentoEdital());
        }
        if (dto.dataRecursoEdital() != null) {
            registroExistente.setDataRecursoEdital(dto.dataRecursoEdital());
        }
        if (dto.dataResultadoEdital() != null) {
            registroExistente.setDataResultadoEdital(dto.dataResultadoEdital());
        }
        if (dto.dataRecursoFinalEdital() != null) {
            registroExistente.setDataRecursoFinalEdital(dto.dataRecursoFinalEdital());
        }
        if (dto.dataBolsaEdital() != null) {
            registroExistente.setDataBolsaEdital(dto.dataBolsaEdital());
        }
        if (dto.informacaoChamadasEdital() != null) {
            registroExistente.setInformacaoChamadasEdital(dto.informacaoChamadasEdital());
        }

        // Salva a entidade atualizada no banco de dados e retorna o objeto atualizado
        return repo.save(registroExistente);
    }

    public Edital findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Edital não encontrado para o ID fornecido."));
    }

    // Método específico para atualização de Edital com os campos do DTO
    public Edital updateDados(EditalDadosDto dto, MultipartFile file) {
        // Busca a entidade existente no banco de dados usando o ID fornecido no DTO
        Edital registroExistente = findById(dto.idEdital());

        // Atualiza apenas os campos fornecidos no DTO
        if (dto.tituloEdital() != null) {
            registroExistente.setTituloEdital(dto.tituloEdital());
        }
        if (dto.identificacaoEdital() != null) {
            registroExistente.setIdentificacaoEdital(dto.identificacaoEdital());
        }
        if (dto.numeroAnoEdital() != null) {
            registroExistente.setNumeroAnoEdital(dto.numeroAnoEdital());
        }
        if (dto.programa() != null) {
            Programa programa = programaRepository.findById(dto.programa())
                    .orElseThrow(() -> new EntityNotFoundException("Programa não encontrado para o ID fornecido."));

            registroExistente.setPrograma(programa);
        }
        if (dto.nomeFormularioEdital() != null) {
            registroExistente.setNomeFormularioEdital(dto.nomeFormularioEdital());
        }
        if (dto.codUnidadeAdmEdital() != 0) {
            registroExistente.setCodUnidadeAdmEdital(dto.codUnidadeAdmEdital());
        }
        if (dto.textoChamadaEdital() != null) {
            registroExistente.setTextoChamadaEdital(dto.textoChamadaEdital());
        }
        if (dto.duracaoPropostaEdital() != null) {
            registroExistente.setDuracaoPropostaEdital(dto.duracaoPropostaEdital());
        }
        registroExistente.setMultiplasPropostaEdital(dto.multiplasPropostaEdital());
        registroExistente.setCordenadorParticipaEdital(dto.cordenadorParticipaEdital());
        registroExistente.setCargaHorariaEdital(dto.cargaHorariaEdital());
        registroExistente.setEscolheDuracaoEdital(dto.escolheDuracaoEdital());
        registroExistente.setObgOrientadorEdital(dto.obgOrientadorEdital());
        registroExistente.setCordenadorBolsaEdital(dto.cordenadorBolsaEdital());
        registroExistente.setEditalEspecial(dto.editalEspecial());
        registroExistente.setProponenteBolsaEdital(dto.proponenteBolsaEdital());
        registroExistente.setPatenteEdital(dto.patenteEdital());
        registroExistente.setInovacaoTecEdital(dto.inovacaoTecEdital());
        registroExistente.setAutoEticaEdital(dto.autoEticaEdital());
        registroExistente.setTermoAceiteBooleanEdital(dto.termoAceiteBooleanEdital());
        if (dto.termoAceiteTextoEdital() != null) {
            registroExistente.setTermoAceiteTextoEdital(dto.termoAceiteTextoEdital());
        }

        // Processa o upload de arquivo, se fornecido
        if (file != null && !file.isEmpty()) {
            try {
                // Verifica se o diretório existe e, se não existir, cria
                Path diretorio = Paths.get(caminhoFile);
                if (!Files.exists(diretorio)) {
                    Files.createDirectories(diretorio);
                }

                // Cria o caminho completo para o arquivo
                Path caminho = Paths.get(caminhoFile,
                        String.valueOf(registroExistente.getIdEdital()) + " " + file.getOriginalFilename());

                // Salva o arquivo na localização especificada
                Files.write(caminho, file.getBytes());

                // Define o nome do arquivo salvo no objeto Arquivo
                registroExistente.setFileEdital(
                        String.valueOf(registroExistente.getIdEdital()) + " " + file.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException("Erro ao salvar arquivo: " + e.getMessage());
            }
        }

        // Salva a entidade atualizada no banco de dados
        return repo.save(registroExistente);
    }

    public Edital deleteRestricoes(Long id) {
        Edital registro = this.get(id);

        if (registro == null) {
            throw new EntityNotFoundException("Restrição não encontrado para o ID fornecido.");
        }

        registro.setProponenteCurriculoEdital(false);
        registro.setMembroCurriculoEdital(false);
        registro.setHabilitaVinculoEmpregaEdital(false);
        registro.setProponenteVinculoEmpregaEdital(false);
        registro.setHabilitaVinculoInstituicaoEdital(false);
        registro.setProponenteVinculoInstituicaoEdital(false);
        registro.setProponenteNivelAcademicoEdital(null);

        return repo.save(registro);
    }

    public Edital updateRestricoes(EditalRestricoesDto dto) {
        Edital registroExistente = repo.findById(dto.idEdital())
                .orElseThrow(() -> new EntityNotFoundException("Restrições não encontrado para o ID fornecido."));
        
        registroExistente.setProponenteCurriculoEdital(dto.proponenteCurriculoEdital());
        registroExistente.setMembroCurriculoEdital(dto.membroCurriculoEdital());
        registroExistente.setHabilitaVinculoEmpregaEdital(dto.habilitaVinculoEmpregaEdital());
        registroExistente.setProponenteVinculoEmpregaEdital(dto.proponenteVinculoEmpregaEdital());
        registroExistente.setHabilitaVinculoInstituicaoEdital(dto.habilitaVinculoInstituicaoEdital());
        registroExistente.setProponenteVinculoInstituicaoEdital(dto.proponenteVinculoInstituicaoEdital());
        registroExistente.setProponenteNivelAcademicoEdital(dto.proponenteNivelAcademicoEdital());

        return repo.save(registroExistente);
    }

    public Edital deletePlanoApresentacao(Long id) {
        // Busca a entidade existente no banco de dados usando o ID fornecido
        Edital registro = this.get(id);
    
        // Verifica se o registro foi encontrado
        if (registro == null) {
            throw new EntityNotFoundException("Plano de apresentação não encontrado para o ID fornecido.");
        }
    
        // Zera ou define como nulos os campos desejados do plano de apresentação
        registro.setExperienciaCordenadorEdital(false);
        registro.setObjetivoGeralEdital(false);
        registro.setObjetivoEspecificoEdital(false);
        registro.setMetodologiaEdital(false);
        registro.setMetodoMatEdital(false);
        registro.setResultadosEdital(false);
        registro.setImpactoEsperadoEdital(false);
        registro.setImpactoDiscriminadoEdital(false);
        registro.setRiscoAtividadeEdital(false);
        registro.setReferenciaBlibEdital(false);
        registro.setEstadoArteEdital(false);
        registro.setJustCopInterEdital(false);
        registro.setQualiParceriasEdital(false);
        registro.setObrasInstalEdital(false);
        registro.setProdBlibEdital(false);
        registro.setProdCulturaEdital(false);
        registro.setProdTecEdital(false);
        registro.setProdDifuEdital(false);
    
        // Salva o registro atualizado no banco de dados
        return repo.save(registro);
    }

    public Edital updatePlanoApresentacao(EditalPlanoApresentacaoDto dto) {
        Edital registroExistente = repo.findById(dto.idEdital())
                .orElseThrow(() -> new EntityNotFoundException("Plano de apresentação não encontrado para o ID fornecido."));

        // Atualiza os campos booleanos sem checar por null
        registroExistente.setExperienciaCordenadorEdital(dto.experienciaCordenadorEdital());
        registroExistente.setObjetivoGeralEdital(dto.objetivoGeralEdital());
        registroExistente.setObjetivoEspecificoEdital(dto.objetivoEspecificoEdital());
        registroExistente.setMetodologiaEdital(dto.metodologiaEdital());
        registroExistente.setMetodoMatEdital(dto.metodoMatEdital());
        registroExistente.setResultadosEdital(dto.resultadosEdital());
        registroExistente.setImpactoEsperadoEdital(dto.impactoEsperadoEdital());
        registroExistente.setImpactoDiscriminadoEdital(dto.impactoDiscriminadoEdital());
        registroExistente.setRiscoAtividadeEdital(dto.riscoAtividadeEdital());
        registroExistente.setReferenciaBlibEdital(dto.referenciaBlibEdital());
        registroExistente.setEstadoArteEdital(dto.estadoArteEdital());
        registroExistente.setJustCopInterEdital(dto.justCopInterEdital());
        registroExistente.setQualiParceriasEdital(dto.qualiParceriasEdital());
        registroExistente.setObrasInstalEdital(dto.obrasInstalEdital());
        registroExistente.setProdBlibEdital(dto.prodBlibEdital());
        registroExistente.setProdCulturaEdital(dto.prodCulturaEdital());
        registroExistente.setProdTecEdital(dto.prodTecEdital());
        registroExistente.setProdDifuEdital(dto.prodDifuEdital());

        // Salva a entidade atualizada no banco de dados e retorna o objeto atualizado
        return repo.save(registroExistente);
    }
    public Edital FindById(Long id) {
        Optional<Edital> edital = repo.findById(id);
        
        if (edital.isPresent()) {
            return edital.get();
        } else {
            throw new EntityNotFoundException("Instituição com ID " + id + " não encontrada");
        }
    }
}