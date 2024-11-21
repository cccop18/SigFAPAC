package fapacapi.service;
import java.util.Optional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.FaixaFinanciamento;
import fapacapi.repository.FaixaFinanciamentoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class FaixaFinanciamentoService implements IService<FaixaFinanciamento> {

    private final FaixaFinanciamentoRepository repo;

    public FaixaFinanciamentoService(FaixaFinanciamentoRepository repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "FaixaFinanciamento",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<FaixaFinanciamento> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "FaixaFinanciamento", unless = "#result == null")
    public FaixaFinanciamento get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "faixafinanciamento", key = "#objeto.id"),
        @CacheEvict(value = "faixasdefinanciamentos", allEntries = true)
    })
    public FaixaFinanciamento save(FaixaFinanciamento objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "FaixaFinancimento", key = "#id"),
        @CacheEvict(value = "faixasdefinanciamento", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public FaixaFinanciamento FindById(Long id) {
       Optional<FaixaFinanciamento> FaixaFinanciamento = repo.findById(id);
        
        if (FaixaFinanciamento.isPresent()) {
            return FaixaFinanciamento.get();
        } else {
            throw new EntityNotFoundException("FaixaFinanciamento com ID " + id + " não encontrada");
        }
    }
    
}
