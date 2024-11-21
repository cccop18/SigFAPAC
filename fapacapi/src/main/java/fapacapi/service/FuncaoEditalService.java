package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.FuncaoEdital;
import fapacapi.repository.FuncaoEditalRepository;

@Service
public class FuncaoEditalService implements IService<FuncaoEdital>{

    private final FuncaoEditalRepository repo;

    public FuncaoEditalService(FuncaoEditalRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "funcoeseditais",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<FuncaoEdital> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "funcaoedital", unless = "#result == null")
    public FuncaoEdital get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "funcaoedital", key = "#objeto.id"),
        @CacheEvict(value = "funcoeseditais", allEntries = true)
    })
    public FuncaoEdital save(FuncaoEdital objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "funcaoedital", key = "#objeto.id"),
        @CacheEvict(value = "funcoeseditais", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}