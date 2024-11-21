package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.PesquisadorConhecimento;
import fapacapi.repository.PesquisadorConhecimentoRepository;

@Service
public class PesquisadorConhecimentoService implements IService<PesquisadorConhecimento> {
    
    private final PesquisadorConhecimentoRepository repo;

    public PesquisadorConhecimentoService(PesquisadorConhecimentoRepository repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "pesquisadoresConhecimentos",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<PesquisadorConhecimento> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "pesquisadorConhecimento", unless = "#result == null")
    public PesquisadorConhecimento get(Long id) {
       return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "pesquisadorConhecimento", key = "#objeto.id"),
        @CacheEvict(value = "pesquisadoresConhecimentos", allEntries = true)
    })
    public PesquisadorConhecimento save(PesquisadorConhecimento objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "pesquisadorConhecimento", key = "#objeto.id"),
        @CacheEvict(value = "pesquisadoresConhecimentos", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
