package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.Membros;
import fapacapi.repository.MembrosRepository;

@Service
public class MembrosService implements IService<Membros> {

    private final MembrosRepository repo;

    public MembrosService(MembrosRepository repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "membros",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<Membros> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "membro", unless = "#result == null")
    public Membros get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "membro", key = "#objeto.id"),
        @CacheEvict(value = "membros", allEntries = true)
    })
    public Membros save(Membros objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "membro", key = "#id"),
        @CacheEvict(value = "membros", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
