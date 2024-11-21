package fapacapi.service;

import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.ParecerProposta;
import fapacapi.repository.ParecerPropostaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ParecerPropostaService implements IService<ParecerProposta>{

    private final ParecerPropostaRepository repo;

    public ParecerPropostaService(ParecerPropostaRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "parecerPropostas",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<ParecerProposta> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "parecerPropostas", unless = "#result == null")
    public ParecerProposta get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "parecerProposta", key = "#objeto.id"),
        @CacheEvict(value = "parecerPropostas", allEntries = true)
    })
    public ParecerProposta save(ParecerProposta objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "parecerProposta", key = "#id"),
        @CacheEvict(value = "parecerPropostas", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
    public ParecerProposta FindById(Long id) {
        Optional<ParecerProposta> parecerProposta = repo.findById(id);
        
        if (parecerProposta.isPresent()) {
            return parecerProposta.get();
        } else {
            throw new EntityNotFoundException("ParecerProposta com ID " + id + " não encontrada");
        }
    }
}

