package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.DiariaProposta;
import fapacapi.repository.DiariaPropostaRepository;

@Service
public class DiariaPropostaService implements IService<DiariaProposta> {

    private final DiariaPropostaRepository repo;

    public DiariaPropostaService(DiariaPropostaRepository repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "diariaspropostas",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<DiariaProposta> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "diariaproposta", unless = "#result == null")
    public DiariaProposta get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "diariaproposta", key = "#objeto.id"),
        @CacheEvict(value = "diariaspropostas", allEntries = true)
    })
    public DiariaProposta save(DiariaProposta objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "diariaproposta", key = "#id"),
        @CacheEvict(value = "diariaspropostas", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
