package fapacapi.service;

import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.Pesquisador;
import fapacapi.repository.PesquisadorRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PesquisadorService implements IService<Pesquisador> {
    
    private final PesquisadorRepository repo;

    public PesquisadorService(PesquisadorRepository repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "pesquisadores",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<Pesquisador> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "pesquisador", unless = "#result == null")
    public Pesquisador get(Long id) {
       return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "pesquisador", key = "#objeto.id"),
        @CacheEvict(value = "pesquisadores", allEntries = true)
    })
    public Pesquisador save(Pesquisador objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "pesquisador", key = "#objeto.id"),
        @CacheEvict(value = "pesquisadores", allEntries = true)
    })
    public void delete(Long id) {
        Pesquisador registro = this.get(id);
        registro.setAtivo(false);
        repo.save(registro);
    }
    public Pesquisador FindById(Long id) {
        Optional<Pesquisador> pesquisador = repo.findById(id);
        
        if (pesquisador.isPresent()) {
            return pesquisador.get();
        } else {
            throw new EntityNotFoundException("Instituição com ID " + id + " não encontrada");
        }
    }
}
