package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.EncargoProposta;
import fapacapi.repository.EncargoPropostaRepository;

@Service
public class EncargoPropostaService implements IService<EncargoProposta> {

    private final EncargoPropostaRepository repo;

    public EncargoPropostaService(EncargoPropostaRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "encargos",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<EncargoProposta> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "encargo", unless = "#result == null")
    public EncargoProposta get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "encargo", key = "#objeto.id"),
        @CacheEvict(value = "encargos", allEntries = true)
    })
    public EncargoProposta save(EncargoProposta objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "encargo", key = "#objeto.id"),
        @CacheEvict(value = "encargos", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
