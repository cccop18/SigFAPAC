package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.PassagemProposta;
import fapacapi.repository.PassagemPropostaRepository;

@Service
public class PassagemPropostaService implements IService<PassagemProposta> {

    private final PassagemPropostaRepository repo;

    public PassagemPropostaService(PassagemPropostaRepository repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "passagens",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<PassagemProposta> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "passagem", unless = "#result == null")
    public PassagemProposta get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "passagem", key = "#objeto.id"),
        @CacheEvict(value = "passagens", allEntries = true)
    })
    public PassagemProposta save(PassagemProposta objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "passagem", key = "#id"),
        @CacheEvict(value = "passagens", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
