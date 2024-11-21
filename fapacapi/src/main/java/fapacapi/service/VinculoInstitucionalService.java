package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// import fapacapi.model.Instituicao;
// import fapacapi.model.Pesquisador;
// import fapacapi.model.UnidadeInstituicao;
import fapacapi.model.VinculoInstitucional;
import fapacapi.repository.InstituicaoRepository;
import fapacapi.repository.PesquisadorRepository;
import fapacapi.repository.UnidadeInstituicaoRepository;
import fapacapi.repository.VinculoInstitucionalRepository;

@Service
public class VinculoInstitucionalService implements IService<VinculoInstitucional> {

    private final VinculoInstitucionalRepository repo;
    @SuppressWarnings("unused")
    private final InstituicaoRepository instituicaoRepo;
    @SuppressWarnings("unused")
    private final UnidadeInstituicaoRepository unidadeInstituicaoRepo;
    @SuppressWarnings("unused")
    private final PesquisadorRepository pesquisadorRepo;

    public VinculoInstitucionalService(VinculoInstitucionalRepository repo, 
                                       InstituicaoRepository instituicaoRepo, 
                                       UnidadeInstituicaoRepository unidadeInstituicaoRepo, 
                                       PesquisadorRepository pesquisadorRepo) {
        this.repo = repo;
        this.instituicaoRepo = instituicaoRepo;
        this.unidadeInstituicaoRepo = unidadeInstituicaoRepo;
        this.pesquisadorRepo = pesquisadorRepo;
    }

    @Override
    @Cacheable(
        value = "vinculosinstitucionals",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<VinculoInstitucional> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "vinculoinstitucional", unless = "#result == null")
    public VinculoInstitucional get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "vinculoinstitucional", key = "#objeto.id"),
        @CacheEvict(value = "vinculosinstitucionals", allEntries = true)
    })
    public VinculoInstitucional save(VinculoInstitucional objeto) {
    
        // // Verifica se o ID da instituição foi fornecido
        // if (objeto.getInstituicao() == null || objeto.getInstituicao().getIdInstituicao() == null) {
        //     throw new IllegalArgumentException("ID da instituição não pode ser nulo.");
        // }
    
        // // Verifica se o ID da unidade da instituição foi fornecido
        // if (objeto.getUnidadeInstituicao() == null || objeto.getUnidadeInstituicao().getIdUnidadeInstituicao() == null) {
        //     throw new IllegalArgumentException("ID da unidade da instituição não pode ser nulo.");
        // }
    
        // // Verifica se o ID do pesquisador foi fornecido
        // if (objeto.getPesquisador() == null || objeto.getPesquisador().getIdPesquisador() == null) {
        //     throw new IllegalArgumentException("ID do pesquisador não pode ser nulo.");
        // }
    
        // // Carregar a instituição do banco de dados
        // Instituicao instituicao = instituicaoRepo.findById(objeto.getInstituicao().getIdInstituicao())
        //     .orElseThrow(() -> new IllegalArgumentException("Instituição não encontrada com o ID: " + objeto.getInstituicao().getIdInstituicao()));
    
        // // Carregar a unidade da instituição
        // UnidadeInstituicao unidadeInstituicao = unidadeInstituicaoRepo.findById(objeto.getUnidadeInstituicao().getIdUnidadeInstituicao())
        //     .orElseThrow(() -> new IllegalArgumentException("Unidade da instituição não encontrada com o ID: " + objeto.getUnidadeInstituicao().getIdUnidadeInstituicao()));
    
        // // Carregar o pesquisador
        // Pesquisador pesquisador = pesquisadorRepo.findById(objeto.getPesquisador().getIdPesquisador())
        //     .orElseThrow(() -> new IllegalArgumentException("Pesquisador não encontrado com o ID: " + objeto.getPesquisador().getIdPesquisador()));
    
        // // Atualizar as entidades associadas
        // objeto.setInstituicao(instituicao);
        // objeto.setUnidadeInstituicao(unidadeInstituicao);
        // objeto.setPesquisador(pesquisador);
    
        // Salvar o objeto VinculoInstitucional
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "vinculoinstitucional", key = "#id"),
        @CacheEvict(value = "vinculosinstitucionals", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
}