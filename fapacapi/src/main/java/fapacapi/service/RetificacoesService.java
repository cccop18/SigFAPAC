package fapacapi.service;

import java.time.LocalDateTime;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.Retificacoes;
import fapacapi.repository.RetificacoesRepository;
import jakarta.transaction.Transactional;

@Service
public class RetificacoesService implements IService<Retificacoes>{

    private final RetificacoesRepository repo;

    public RetificacoesService(RetificacoesRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "retifacoes",
        condition = "#termoBusca == null or #termoBusca.isBlanck()"
    )
    public Page<Retificacoes> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "retificacoa", unless = "#result == null")
    public Retificacoes get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "retificacao", key = "#objeto.id"),
        @CacheEvict(value = "retificacoes", allEntries = true)
    })
    public Retificacoes save(Retificacoes objeto) {
        if(objeto.getDataRetificacoes() == null || objeto.getDataRetificacoes().isBefore(LocalDateTime.now())){
            objeto.setDataRetificacoes(LocalDateTime.now());
        }
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "retificacao", key = "#objeto.id"),
        @CacheEvict(value = "reticacoes", allEntries = true)
    })
    @Transactional
    public void delete(Long id) {
        Retificacoes registro = this.get(id);
        registro.setAtivoRetificacoes(false);;
        repo.save(registro);
    }
    
}
