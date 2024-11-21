package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.Diarias;
import fapacapi.repository.DiariasRepository;

@Service
public class DiariasService implements IService<Diarias>{

    private final DiariasRepository repo;

    public DiariasService(DiariasRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "diarias",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<Diarias> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "diaria", unless = "#result == null")
    public Diarias get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "diaria", key = "#objeto.id"),
        @CacheEvict(value = "diarias", allEntries = true)
    })
    public Diarias save(Diarias objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "diaria", key = "#objeto.id"),
        @CacheEvict(value = "diarias", allEntries = true)
    })
    public void delete(Long id) {
        Diarias registro = this.get(id);
        registro.setAtivo(false);
        repo.save(registro);
    }
    
}