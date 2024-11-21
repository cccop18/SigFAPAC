package fapacapi.service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

import fapacapi.model.PrimeiraSubArea;
import fapacapi.repository.PrimeiraSubAreaRepository;
import jakarta.persistence.EntityNotFoundException;
@Service
public class PrimeiraSubAreaService  implements IService<PrimeiraSubArea>{
    @Autowired
    private final PrimeiraSubAreaRepository repo;

    public PrimeiraSubAreaService(PrimeiraSubAreaRepository repo) {
        
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "PrimeiraSubArea",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<PrimeiraSubArea> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);  // Busca por termo
        }
    }

    @Override
    @Cacheable(value = "PrimeiraSubArea", unless = "#result == null")
    public PrimeiraSubArea get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "PrimeiraSubArea", key = "#objeto.id"),
        @CacheEvict(value = "PrimeiraSubArea", allEntries = true)
    })
    public PrimeiraSubArea save(PrimeiraSubArea objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "PrimeiraSubArea", key = "#id"),
        @CacheEvict(value = "PrimeiraSubArea", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
    public List<PrimeiraSubArea> getFk(Long fk) {
        if (fk == null) {
            return repo.findAll();  // Retorna todas se fk for null
        } else {
            return repo.findByAreaConhecimentoId(fk);  // Busca pela chave estrangeira
        }
    }
    public PrimeiraSubArea findById(Long idPrimeiraSubArea) {
        return repo.findById(idPrimeiraSubArea)
            .orElseThrow(() -> new EntityNotFoundException("Primeira Subárea com ID " + idPrimeiraSubArea + " não encontrada"));
    }
}