package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.HospedagemAlimentacaoProposta;
import fapacapi.repository.HospedagemAlimentacaoPropostaRepository;

@Service
public class HospedagemAlimentacaoPropostaService implements IService<HospedagemAlimentacaoProposta>{

    private final HospedagemAlimentacaoPropostaRepository repo;

    public HospedagemAlimentacaoPropostaService(HospedagemAlimentacaoPropostaRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "hospedagens",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<HospedagemAlimentacaoProposta> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "hospedagem", unless = "#result == null")
    public HospedagemAlimentacaoProposta get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "hospedagem", key = "#objeto.id"),
        @CacheEvict(value = "hospedagens", allEntries = true)
    })
    public HospedagemAlimentacaoProposta save(HospedagemAlimentacaoProposta objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "hospedagem", key = "#objeto.id"),
        @CacheEvict(value = "hospedagens", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}