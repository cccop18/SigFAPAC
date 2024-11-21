package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.Programa;
import fapacapi.repository.ProgramaRepository;

@Service
public class ProgramaService implements IService<Programa>{

    private final ProgramaRepository repo;

    public ProgramaService(ProgramaRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "programas",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<Programa> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "programa", unless = "#result == null")
    public Programa get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "programa", key = "#objeto.id"),
        @CacheEvict(value = "programas", allEntries = true)
    })
    public Programa save(Programa objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "programa", key = "#objeto.id"),
        @CacheEvict(value = "programas", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
