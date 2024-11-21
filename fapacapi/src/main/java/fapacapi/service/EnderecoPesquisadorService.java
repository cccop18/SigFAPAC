package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.EnderecoPesquisador;
import fapacapi.repository.EnderecoPesquisadorRepository;

@Service
public class EnderecoPesquisadorService implements IService<EnderecoPesquisador> {

    private final EnderecoPesquisadorRepository repo;

    public EnderecoPesquisadorService(EnderecoPesquisadorRepository repo) {
        this.repo = repo;
    }

    @Override
    // @Cacheable(
    //     value = "enderecospesquisadores",
    //     condition = "#termoBusca == null or #termoBusca.isBlank()"
    // )
    public Page<EnderecoPesquisador> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "enderecopesquisador", unless = "#result == null")
    public EnderecoPesquisador get(Long id) {
       return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "enderecopesquisador", key = "#objeto.id"),
        @CacheEvict(value = "enderecospesquisadores", allEntries = true)
    })
    public EnderecoPesquisador save(EnderecoPesquisador objeto) {
        return repo.save(objeto);
    }

    @Caching(evict = {
        @CacheEvict(value = "enderecopesquisador", key = "#objeto.id"),
        @CacheEvict(value = "enderecospesquisadores", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}