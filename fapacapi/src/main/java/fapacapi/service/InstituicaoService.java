package fapacapi.service;

import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.repository.InstituicaoRepository;
import jakarta.persistence.EntityNotFoundException;
import fapacapi.model.Instituicao;

@Service
public class InstituicaoService implements IService<Instituicao>{

    private final InstituicaoRepository repo;

    public InstituicaoService(InstituicaoRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "instituicoes",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<Instituicao> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "instituicao", unless = "#result == null")
    public Instituicao get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "instituicao", key = "#objeto.id"),
        @CacheEvict(value = "instituicoes", allEntries = true)
    })
    public Instituicao save(Instituicao objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "instituicao", key = "#id"),
        @CacheEvict(value = "instituicoes", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Instituicao FindById(Long id) {
        Optional<Instituicao> instituicao = repo.findById(id);
        
        if (instituicao.isPresent()) {
            return instituicao.get();
        } else {
            throw new EntityNotFoundException("Instituição com ID " + id + " não encontrada");
        }
    }
    
}