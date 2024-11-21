package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.Arquivo;
import fapacapi.repository.ArquivoRepository;

@Service
public class ArquivoService implements IService<Arquivo>{

    private final ArquivoRepository repo;

    public ArquivoService(ArquivoRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "arquivos",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<Arquivo> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "arquivo", unless = "#result == null")
    public Arquivo get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "arquivo", key = "#objeto.id"),
        @CacheEvict(value = "arquivos", allEntries = true)
    })
    public Arquivo save(Arquivo objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "arquivo", key = "#objeto.id"),
        @CacheEvict(value = "arquivos", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}