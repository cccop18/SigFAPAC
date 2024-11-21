package fapacapi.service;

import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.AtividadeProposta;
import fapacapi.repository.AtividadePropostaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AtividadePropostaService implements IService<AtividadeProposta>{

    private final AtividadePropostaRepository repo;

    public AtividadePropostaService(AtividadePropostaRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "AtividadePropostas",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<AtividadeProposta> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "AtividadeProposta", unless = "#result == null")
    public AtividadeProposta get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "AtividadeProposta", key = "#objeto.id"),
        @CacheEvict(value = "AtividadePropostas", allEntries = true)
    })
    public AtividadeProposta save(AtividadeProposta objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "AtividadeProposta", key = "#id"),
        @CacheEvict(value = "AtividadePropostas", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public AtividadeProposta FindById(Long id) {
        Optional<AtividadeProposta> atividadeProposta = repo.findById(id);
        
        if (atividadeProposta.isPresent()) {
            return atividadeProposta.get();
        } else {
            throw new EntityNotFoundException("AtividadeProposta com ID " + id + " não encontrada");
        }
    } 
    
}
