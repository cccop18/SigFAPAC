package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.MaterialProposta;
import fapacapi.repository.MaterialPropostaRepository;

@Service
public class MaterialPropostaService implements IService<MaterialProposta> {

    private final MaterialPropostaRepository repo;

    public MaterialPropostaService(MaterialPropostaRepository repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "materiais",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<MaterialProposta> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "material", unless = "#result == null")
    public MaterialProposta get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "material", key = "#objeto.id"),
        @CacheEvict(value = "materiais", allEntries = true)
    })
    public MaterialProposta save(MaterialProposta objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "material", key = "#id"),
        @CacheEvict(value = "materiais", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
