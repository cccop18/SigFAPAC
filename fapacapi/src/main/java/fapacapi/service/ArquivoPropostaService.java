package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.ArquivoProposta;
import fapacapi.repository.ArquivoPropostaRepository;
@Service
public class ArquivoPropostaService  implements IService<ArquivoProposta>{

    private final ArquivoPropostaRepository repo;

    public ArquivoPropostaService(ArquivoPropostaRepository repo) {
        this.repo = repo;
    }
    @Override
    @Cacheable(
        value = "arquivoProposta",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<ArquivoProposta> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "arquivoproposta", unless = "#result == null")
    public ArquivoProposta get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "arquivoproposta", key = "#objeto.id"),
        @CacheEvict(value = "propostasarquivos", allEntries = true)
    })
    public ArquivoProposta save(ArquivoProposta objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "arquivoproposta", key = "#objeto.id"),
        @CacheEvict(value = "propostasarquivos", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}


