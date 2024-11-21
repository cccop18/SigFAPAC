package fapacapi.service;

import java.util.Optional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.VinculoProposta;
import fapacapi.repository.VinculoPropostaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class VinculoPropostaService implements IService<VinculoProposta>{

    private final VinculoPropostaRepository repo;

    public VinculoPropostaService(VinculoPropostaRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "vinculoPropostas",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<VinculoProposta> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "vinculoProposta", unless = "#result == null")
    public VinculoProposta get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "vinculoProposta", key = "#objeto.id"),
        @CacheEvict(value = "vinculoPropostas", allEntries = true)
    })
    public VinculoProposta save(VinculoProposta objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "vinculoProposta", key = "#id"),
        @CacheEvict(value = "vinculoPropostas", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public VinculoProposta FindById(Long id) {
        Optional<VinculoProposta> vinculoProposta = repo.findById(id);
        
        if (vinculoProposta.isPresent()) {
            return vinculoProposta.get();
        } else {
            throw new EntityNotFoundException("Vinculo Proposta com ID " + id + " não encontrada");
        }
    }

}
