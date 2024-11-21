package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.AbrangenciaProposta;
import fapacapi.repository.AbrangenciaPropostaRepository;

@Service
public class AbrangenciaPropostaService implements IService<AbrangenciaProposta> {

    private final AbrangenciaPropostaRepository repo;

    public AbrangenciaPropostaService(AbrangenciaPropostaRepository repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "abrangenciaspropostas",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<AbrangenciaProposta> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "abrangenciaproposta", unless = "#result == null")
    public AbrangenciaProposta get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "abrangenciaproposta", key = "#objeto.id"),
        @CacheEvict(value = "abrangenciaspropostas", allEntries = true)
    })
    public AbrangenciaProposta save(AbrangenciaProposta objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "abrangenciaproposta", key = "#id"),
        @CacheEvict(value = "abrangenciaspropostas", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
