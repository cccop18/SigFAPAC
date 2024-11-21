package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.Rubrica;
import fapacapi.repository.RubricaRepository;

@Service
public class RubricaService implements IService<Rubrica>{

    private final RubricaRepository repo;

    public RubricaService(RubricaRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "rubricas",
        condition = "#termoBusca == null or #termoBusca.isBlanck()"
    )
    public Page<Rubrica> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "rubrica", unless = "#result == null")
    public Rubrica get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "rubrica", key = "#objeto.id"),
        @CacheEvict(value = "rubricas", allEntries = true)
    })
    public Rubrica save(Rubrica objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "rubrica", key = "#objeto.id"),
        @CacheEvict(value = "rubricas", allEntries = true)
    })
    public void delete(Long id) {
        Rubrica registro = this.get(id);
        registro.setAtivoRubrica(false);
        repo.save(registro);
    }
    
}
