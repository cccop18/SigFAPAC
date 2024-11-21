package fapacapi.service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.SegundaSubArea;

import fapacapi.repository.SegundaSubAreaRepository;
import jakarta.persistence.EntityNotFoundException;
@Service
public class SegundaSubAreaService  implements IService<SegundaSubArea>{
    @Autowired
    private final SegundaSubAreaRepository repo;

    public SegundaSubAreaService(SegundaSubAreaRepository repo) {
        
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "SegundaSubArea",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<SegundaSubArea> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "SegundaSubArea", unless = "#result == null")
    public SegundaSubArea get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "SegundaSubArea", key = "#objeto.id"),
        @CacheEvict(value = "SegundaSubArea", allEntries = true)
    })
    public SegundaSubArea save(SegundaSubArea objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "SegundaSubArea", key = "#id"),
        @CacheEvict(value = "SegundaSubArea", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public SegundaSubArea findById(Long idSegundaSubArea) {
        return repo.findById(idSegundaSubArea)
            .orElseThrow(() -> new EntityNotFoundException("Segunda Subárea com ID " + idSegundaSubArea + " não encontrada"));
    }
    
}