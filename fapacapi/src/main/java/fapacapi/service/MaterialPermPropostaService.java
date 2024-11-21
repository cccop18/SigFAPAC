package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.MaterialPermProposta;
import fapacapi.repository.MaterialPermPropostaRepository;

@Service
public class MaterialPermPropostaService implements IService<MaterialPermProposta> {

    private final MaterialPermPropostaRepository repo;

    public MaterialPermPropostaService(MaterialPermPropostaRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "materiaisperm",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<MaterialPermProposta> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "materialperm", unless = "#result == null")
    public MaterialPermProposta get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "materialperm", key = "#objeto.id"),
        @CacheEvict(value = "materiaisperm", allEntries = true)
    })
    public MaterialPermProposta save(MaterialPermProposta objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "materialperm", key = "#objeto.id"),
        @CacheEvict(value = "materiaisperm", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}