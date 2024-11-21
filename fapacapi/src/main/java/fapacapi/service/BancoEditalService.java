package fapacapi.service;

import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.BancoEdital;
import fapacapi.repository.BancoEditalRepository;
import jakarta.persistence.EntityNotFoundException;
@Service
public class BancoEditalService  implements IService<BancoEdital> {

     private final BancoEditalRepository repo;

     public BancoEditalService(BancoEditalRepository repo){
        this.repo = repo;
     }

    @Override
    @Cacheable(
        value = "BancoEdital",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<BancoEdital> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "BancoEdital", unless = "#result == null")
    public BancoEdital get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "bancoEdital", key = "#objeto.id"),
        @CacheEvict(value = "bancoseditais", allEntries = true)
    })
    public BancoEdital save(BancoEdital objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "bancoEdital", key = "#id"),
        @CacheEvict(value = "bancoseditais", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public BancoEdital FindById(Long id) {
        Optional<BancoEdital> BancoEdital = repo.findById(id);
        
        if (BancoEdital.isPresent()) {
            return BancoEdital.get();
        } else {
            throw new EntityNotFoundException("BancoEdital com ID " + id + " não encontrada");
        }
    }
    
}