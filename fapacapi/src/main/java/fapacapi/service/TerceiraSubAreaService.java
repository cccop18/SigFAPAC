package fapacapi.service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.TerceiraSubArea;

import fapacapi.repository.TerceiraSubAreaRepository;
import jakarta.persistence.EntityNotFoundException;
@Service
public class TerceiraSubAreaService implements IService<TerceiraSubArea>{
    @Autowired
    private final TerceiraSubAreaRepository repo;

    public TerceiraSubAreaService(TerceiraSubAreaRepository repo) {
        
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "TerceiraSubArea",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<TerceiraSubArea> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "TerceiraSubArea", unless = "#result == null")
    public TerceiraSubArea get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "TerceiraSubArea", key = "#objeto.id"),
        @CacheEvict(value = "TerceiraSubArea", allEntries = true)
    })
    public TerceiraSubArea save(TerceiraSubArea objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "TerceiraSubArea", key = "#id"),
        @CacheEvict(value = "TerceiraSubArea", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public TerceiraSubArea findById(Long idTerceiraSubArea) {
     return repo.findById(idTerceiraSubArea)
            .orElseThrow(() -> new EntityNotFoundException("Terceira Subárea com ID " + idTerceiraSubArea + " não encontrada"));
    }
}