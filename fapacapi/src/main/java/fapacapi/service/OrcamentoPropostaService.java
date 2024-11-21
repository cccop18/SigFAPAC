package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.OrcamentoProposta;
import fapacapi.repository.OrcamentoPropostaRepository;

@Service
public class OrcamentoPropostaService implements IService<OrcamentoProposta> {

    private final OrcamentoPropostaRepository repo;

    public OrcamentoPropostaService(OrcamentoPropostaRepository repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "orcamentos",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<OrcamentoProposta> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "orcamento", unless = "#result == null")
    public OrcamentoProposta get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "orcamento", key = "#objeto.id"),
        @CacheEvict(value = "orcamentos", allEntries = true)
    })
    public OrcamentoProposta save(OrcamentoProposta objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "orcamento", key = "#id"),
        @CacheEvict(value = "orcamentos", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
