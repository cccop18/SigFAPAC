package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.Estado;
import fapacapi.repository.EstadoRepository;

@Service
public class EstadoService implements IService<Estado>{

    private final EstadoRepository repo;

    public EstadoService(EstadoRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "estados",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<Estado> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "estado", unless = "#result == null")
    public Estado get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "estado", key = "#objeto.id"),
        @CacheEvict(value = "estados", allEntries = true)
    })
    public Estado save(Estado objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "estado", key = "#objeto.id"),
        @CacheEvict(value = "estados", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}