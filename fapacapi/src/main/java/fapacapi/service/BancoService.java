package fapacapi.service;

import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.Banco;

import fapacapi.repository.BancoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class BancoService implements IService<Banco> {

    private final BancoRepository repo;

    public BancoService(BancoRepository repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "Banco",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<Banco> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "Banco", unless = "#result == null")
    public Banco get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "banco", key = "#objeto.id"),
        @CacheEvict(value = "bancos", allEntries = true)
    })
    public Banco save(Banco objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "banco", key = "#id"),
        @CacheEvict(value = "bancos", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Banco FindById(Long id) {
        Optional<Banco> Banco = repo.findById(id);
        
        if (Banco.isPresent()) {
            return Banco.get();
        } else {
            throw new EntityNotFoundException("Banco com ID " + id + " não encontrada");
        }
    }
    
}