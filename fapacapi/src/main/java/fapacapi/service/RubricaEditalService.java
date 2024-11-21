package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.RubricaEdital;
import fapacapi.repository.RubricaEditalRepository;

@Service
public class RubricaEditalService implements IService<RubricaEdital>{

    private final RubricaEditalRepository repo;

    public RubricaEditalService(RubricaEditalRepository repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "rubricasEdital",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<RubricaEdital> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "rubricaEdital", unless = "#result == null")
    public RubricaEdital get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "rubricaEdital", key = "#objeto.id"),
        @CacheEvict(value = "rubricasEdital", allEntries = true)
    })
    public RubricaEdital save(RubricaEdital objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "rubricaEdital", key = "#objeto.id"),
        @CacheEvict(value = "rubricasEdital", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
