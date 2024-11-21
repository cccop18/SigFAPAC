package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.Moeda;
import fapacapi.repository.MoedaRepository;

@Service
public class MoedaService implements IService<Moeda> {

    private final MoedaRepository repo;

    public MoedaService(MoedaRepository repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "moedas",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<Moeda> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "moeda", unless = "#result == null")
    public Moeda get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "moeda", key = "#objeto.id"),
        @CacheEvict(value = "moedas", allEntries = true)
    })
    public Moeda save(Moeda objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "moeda", key = "#id"),
        @CacheEvict(value = "moedas", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
