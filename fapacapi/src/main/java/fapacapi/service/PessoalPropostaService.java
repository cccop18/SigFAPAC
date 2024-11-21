package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.PessoalProposta;
import fapacapi.repository.PessoalPropostaRepository;

@Service
public class PessoalPropostaService implements IService<PessoalProposta> {

    private final PessoalPropostaRepository repo;

    public PessoalPropostaService(PessoalPropostaRepository repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "pessoais",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<PessoalProposta> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "pessoal", unless = "#result == null")
    public PessoalProposta get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "pessoal", key = "#objeto.id"),
        @CacheEvict(value = "pessoais", allEntries = true)
    })
    public PessoalProposta save(PessoalProposta objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "pessoal", key = "#id"),
        @CacheEvict(value = "pessoais", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
