package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.BolsaProposta;
import fapacapi.repository.BolsaPropostaRepository;

@Service
public class BolsaPropostaService implements IService<BolsaProposta> {

    private final BolsaPropostaRepository repo;

    public BolsaPropostaService(BolsaPropostaRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "bolsas",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<BolsaProposta> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "bolsa", unless = "#result == null")
    public BolsaProposta get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "bolsa", key = "#objeto.id"),
        @CacheEvict(value = "bolsas", allEntries = true)
    })
    public BolsaProposta save(BolsaProposta objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "bolsa", key = "#objeto.id"),
        @CacheEvict(value = "bolsas", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
