package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.ArquivoEdital;
import fapacapi.repository.ArquivoEditalRepository;

@Service
public class ArquivoEditalService implements IService<ArquivoEdital>{

    private final ArquivoEditalRepository repo;

    public ArquivoEditalService(ArquivoEditalRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "arquivoseditais",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<ArquivoEdital> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "arquivoedital", unless = "#result == null")
    public ArquivoEdital get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "arquivoedital", key = "#objeto.id"),
        @CacheEvict(value = "arquivoseditais", allEntries = true)
    })
    public ArquivoEdital save(ArquivoEdital objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "arquivoedital", key = "#objeto.id"),
        @CacheEvict(value = "arquivoseditais", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}