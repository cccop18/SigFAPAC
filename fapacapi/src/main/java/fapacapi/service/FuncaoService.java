package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.Funcao;
import fapacapi.repository.FuncaoRepository;

@Service
public class FuncaoService implements IService<Funcao>{

    private final FuncaoRepository repo;

    public FuncaoService(FuncaoRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "funcoes",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<Funcao> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "funcao", unless = "#result == null")
    public Funcao get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "funcao", key = "#objeto.id"),
        @CacheEvict(value = "funcoes", allEntries = true)
    })
    public Funcao save(Funcao objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "funcao", key = "#objeto.id"),
        @CacheEvict(value = "funcoes", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}