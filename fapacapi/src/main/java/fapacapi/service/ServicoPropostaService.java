package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.ServicoProposta;
import fapacapi.repository.ServicoPropostaRepository;

@Service
public class ServicoPropostaService implements IService<ServicoProposta> {

    private final ServicoPropostaRepository repo;

    public ServicoPropostaService(ServicoPropostaRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "servicos",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<ServicoProposta> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "servico", unless = "#result == null")
    public ServicoProposta get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "servico", key = "#objeto.id"),
        @CacheEvict(value = "servicos", allEntries = true)
    })
    public ServicoProposta save(ServicoProposta objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "servico", key = "#objeto.id"),
        @CacheEvict(value = "servicos", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
