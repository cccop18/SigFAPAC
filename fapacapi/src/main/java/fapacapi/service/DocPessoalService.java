package fapacapi.service;

import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import fapacapi.model.DocPessoal;
import fapacapi.repository.DocPessoalRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DocPessoalService implements IService<DocPessoal>{
   
    private final DocPessoalRepository repo;

    public DocPessoalService(DocPessoalRepository repo){
        this.repo = repo;
    }

    @Override
    @Cacheable(
        value = "DocPessoal",
        condition = "#termoBusca == null or #termoBusca.isBlank()"
    )
    public Page<DocPessoal> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()){
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "documentoPessoal", unless = "#result == null")
    public DocPessoal get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "documentopessoal", key = "#objeto.id"),
        @CacheEvict(value = "documentosspessoais", allEntries = true)
    })
    public DocPessoal save(DocPessoal objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "documentopessoal", key = "#id"),
        @CacheEvict(value = "documentospessoais", allEntries = true)
    })
    public void delete(Long id) {
        DocPessoal registro = this.get(id);
        registro.setAtivo(false);
        repo.save(registro);
    }

    public DocPessoal FindById(Long id) {
        Optional<DocPessoal> DocPessoal = repo.findById(id);
        
        if (DocPessoal.isPresent()) {
            return DocPessoal.get();
        } else {
            throw new EntityNotFoundException("documentoPessoal com ID " + id + " não encontrada");
        }
    }
    
}