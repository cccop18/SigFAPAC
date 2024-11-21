package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.EnderecoUnidade;
import fapacapi.repository.EnderecoUnidadeRepository;

@Service
public class EnderecoUnidadeService implements IService<EnderecoUnidade>{

    private final EnderecoUnidadeRepository repo;

    public EnderecoUnidadeService(EnderecoUnidadeRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "enderecos",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<EnderecoUnidade> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "endereco", unless = "#result == null")
    public EnderecoUnidade get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "endereco", key = "#objeto.id"),
        @CacheEvict(value = "enderecos", allEntries = true)
    })
    public EnderecoUnidade save(EnderecoUnidade objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "endereco", key = "#id"),
        @CacheEvict(value = "enderecos", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}