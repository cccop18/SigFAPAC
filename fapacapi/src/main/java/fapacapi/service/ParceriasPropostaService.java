package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.ParceriasProposta;
import fapacapi.repository.ParceriasPropostaRepository;

@Service
public class ParceriasPropostaService implements IService<ParceriasProposta> {

    private final ParceriasPropostaRepository repo;

    public ParceriasPropostaService(ParceriasPropostaRepository repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "parcerias",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<ParceriasProposta> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "parceria", unless = "#result == null")
    public ParceriasProposta get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "parceria", key = "#objeto.id"),
        @CacheEvict(value = "parcerias", allEntries = true)
    })
    public ParceriasProposta save(ParceriasProposta objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "parceria", key = "#id"),
        @CacheEvict(value = "parcerias", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
