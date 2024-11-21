package fapacapi.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.controller.dto.PropostaTituloDto;
import fapacapi.controller.mapper.PropostaTituloMapper;
import fapacapi.model.Edital;
import fapacapi.model.Instituicao;
import fapacapi.model.Pesquisador;
import fapacapi.model.Proposta;
import fapacapi.model.UnidadeInstituicao;
import fapacapi.model.VinculoProposta;
import fapacapi.repository.PropostaRepostory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PropostaService implements IService<Proposta>{

    private final PropostaRepostory repo;
     private final PropostaTituloMapper mapper;
    private final InstituicaoService instituicaoService;
    @SuppressWarnings("unused")
    private final PropostaConhecimentoService conhecimentoService;
    private final EditalService editalService;
    private final PesquisadorService pesquisadorService;
    private final UnidadeInstituicaoService unidadeInstituicaoService;
    @SuppressWarnings("unused")
    private final AreaConhecimentoService areaConhecimentoService;
    @SuppressWarnings("unused")
    private final PrimeiraSubAreaService primeiraSubAreaService;
    @SuppressWarnings("unused")
    private final SegundaSubAreaService segundaSubAreaService;
    @SuppressWarnings("unused")
    private final TerceiraSubAreaService terceiraSubAreaService;

    public PropostaService(PropostaRepostory repo,
            PropostaTituloMapper mapper,
            PropostaConhecimentoService conhecimentoService,
            InstituicaoService instituicaoService,
            AreaConhecimentoService areaConhecimentoService,
            PrimeiraSubAreaService primeiraSubAreaService,
            SegundaSubAreaService segundaSubAreaService,
            TerceiraSubAreaService terceiraSubAreaService,
            UnidadeInstituicaoService unidadeInstituicaoService,
            EditalService editalService,
            PesquisadorService pesquisadorService) {
        this.repo = repo;
        this.mapper = mapper;
        this.instituicaoService = instituicaoService;
        this.areaConhecimentoService = areaConhecimentoService;
        this.primeiraSubAreaService = primeiraSubAreaService;
        this.segundaSubAreaService = segundaSubAreaService;
        this.terceiraSubAreaService = terceiraSubAreaService;
        this.unidadeInstituicaoService = unidadeInstituicaoService;
        this.editalService = editalService;
        this.conhecimentoService = conhecimentoService;
        this.pesquisadorService = pesquisadorService;
    }

    @Override
    @Cacheable(
        value = "propostas",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<Proposta> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "proposta", unless = "#result == null")
    public Proposta get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "proposta", key = "#objeto.id"),
        @CacheEvict(value = "propostas", allEntries = true)
    })
    public Proposta save(Proposta objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "proposta", key = "#id"),
        @CacheEvict(value = "propostas", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Proposta FindById(Long id) {
        Optional<Proposta> proposta = repo.findById(id);
        
        if (proposta.isPresent()) {
            return proposta.get();
        } else {
            throw new EntityNotFoundException("Proposta com ID " + id + " não encontrada");
        }
    }
    @Transactional
    public PropostaTituloDto criarProposta(PropostaTituloDto objeto) {
        Proposta proposta = new Proposta();
        VinculoProposta vinculoProposta = new VinculoProposta();

        // Verificação do Edital
        Edital editalExistente = editalService.FindById(objeto.idEdital());
        if (editalExistente == null) {
            throw new EntityNotFoundException("Edital com ID " + objeto.idEdital() + " não encontrado");
        }

        Pesquisador pesquisadorExistente= pesquisadorService.FindById(objeto.pesquisador());
        if (pesquisadorExistente == null) {
            throw new EntityNotFoundException("Edital com ID " + objeto.pesquisador() + " não encontrado");
        }

        // Definir os dados da proposta
        proposta.setPesquisador(pesquisadorExistente);
        proposta.setTituloProposta(objeto.tituloProposta());
        proposta.setEdital(editalExistente);
        proposta.setGrupoPesquisa(objeto.grupoPesquisa());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate datainicio = LocalDate.parse(objeto.dataInicioPesquisa(), formatter);
        proposta.setDataInicioPesquisa(datainicio);
        proposta.setDuracaoProposta(objeto.duracaoProposta());
        proposta.setTermoAceiteProposta(objeto.termoAceiteProposta());
        proposta.setPatenteProposta(objeto.patenteProposta());
        proposta.setInovacaoProposta(objeto.inovacaoProposta());
        proposta.setEticaProposta(objeto.eticaProposta());

        proposta.setSituacao("Em edição");
        // Salvar a proposta no banco
        Proposta propostaSalva = save(proposta);

        // Processar vínculo
        Instituicao instituicao = instituicaoService.FindById(objeto.idInstituicao());
        UnidadeInstituicao unidade = unidadeInstituicaoService.FindById(objeto.idUnidade());
        
        if (instituicao == null) {
            throw new EntityNotFoundException("Instituição com ID " + objeto.idInstituicao() + " não encontrada");
        }
        if (unidade == null) {
            throw new EntityNotFoundException("Unidade com ID " + objeto.idUnidade() + " não encontrada");
        }
        vinculoProposta.setInstituicao(instituicao);
        vinculoProposta.setUnidadeInstituicao(unidade);
        vinculoProposta.setProposta(propostaSalva);


        // Retorna o DTO
        return mapper.toDto(propostaSalva);
    }

}
