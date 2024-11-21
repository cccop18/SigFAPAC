package fapacapi.service;

import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.DocPessoalEdital;
import fapacapi.repository.DocPessoalEditalRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DocPessoalEditalService  implements IService<DocPessoalEdital>{

    private final DocPessoalEditalRepository repo;

    public DocPessoalEditalService(DocPessoalEditalRepository repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "DocPessoalEdital",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<DocPessoalEdital> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "DocPessoalEdital", unless = "#result == null")
    public DocPessoalEdital get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "documentopessoaledital", key = "#objeto.id"),
        @CacheEvict(value = "documentospessoaiseditais", allEntries = true)
    })
    public DocPessoalEdital save(DocPessoalEdital objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "docpessoalEdital", key = "#id"),
        @CacheEvict(value = "Documentospessoaiseditais", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public DocPessoalEdital FindById(Long id) {
        Optional<DocPessoalEdital> DocPessoalEdital = repo.findById(id);
        
        if (DocPessoalEdital.isPresent()) {
            return DocPessoalEdital.get();
        } else {
            throw new EntityNotFoundException("DocPessoalEdital com ID " + id + " não encontrada");
        }
    }
    
}


