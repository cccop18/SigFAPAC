package fapacapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.UnidadeInstituicao;
import fapacapi.repository.UnidadeInstituicaoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UnidadeInstituicaoService implements IService<UnidadeInstituicao>{

    private final UnidadeInstituicaoRepository repo;

    public UnidadeInstituicaoService(UnidadeInstituicaoRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "unidades",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<UnidadeInstituicao> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "unidade", unless = "#result == null")
    public UnidadeInstituicao get(Long id) {
       return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "unidade", key = "#objeto.id"),
        @CacheEvict(value = "unidades", allEntries = true)
    })
    public UnidadeInstituicao save(UnidadeInstituicao objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "unidade", key = "#objeto.id"),
        @CacheEvict(value = "unidades", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    @Cacheable(value = "unidadesPorInstituicao", key = "#instituicaoId")
    public List<UnidadeInstituicao> getUnidadesByInstituicao(Long instituicaoId) {
        return repo.findByInstituicaoIdInstituicao(instituicaoId);
    }
    public UnidadeInstituicao FindById(Long id) {
        Optional<UnidadeInstituicao> unidade = repo.findById(id);
        
        if (unidade.isPresent()) {
            return unidade.get();
        } else {
            throw new EntityNotFoundException("Instituição com ID " + id + " não encontrada");
        }
    }
}
