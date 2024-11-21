package fapacapi.service;

import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.PropostaConhecimento;
import fapacapi.repository.PropostaConhecimentoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PropostaConhecimentoService implements IService<PropostaConhecimento>{

    private final PropostaConhecimentoRepository repo;

    public PropostaConhecimentoService(PropostaConhecimentoRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "PropostaConhecimento",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<PropostaConhecimento> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "PropostaConhecimento", unless = "#result == null")
    public PropostaConhecimento get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "PropostaConhecimento", key = "#objeto.id"),
        @CacheEvict(value = "PropostaConhecimentos", allEntries = true)
    })
    public PropostaConhecimento save(PropostaConhecimento objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "PropostaConhecimento", key = "#id"),
        @CacheEvict(value = "PropostaConhecimentos", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public PropostaConhecimento FindById(Long id) {
        Optional<PropostaConhecimento> propostaConhecimento = repo.findById(id);
        
        if (propostaConhecimento.isPresent()) {
            return propostaConhecimento.get();
        } else {
            throw new EntityNotFoundException("PropostaConhecimento com ID " + id + " não encontrada");
        }
    }
    
}
