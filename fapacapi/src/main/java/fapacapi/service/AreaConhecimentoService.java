package fapacapi.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.model.AreaConhecimento;
import fapacapi.model.PrimeiraSubArea;
import fapacapi.model.SegundaSubArea;
import fapacapi.model.TerceiraSubArea;
import fapacapi.repository.AreaConhecimentoRepository;
import fapacapi.repository.PrimeiraSubAreaRepository;
import fapacapi.repository.SegundaSubAreaRepository;
import fapacapi.repository.TerceiraSubAreaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AreaConhecimentoService implements IService<AreaConhecimento> {

    private final AreaConhecimentoRepository repo;
    private final PrimeiraSubAreaRepository primeiraSubAreaRepository;
    private final SegundaSubAreaRepository segundaSubAreaRepository;
    private final TerceiraSubAreaRepository terceiraSubAreaRepository;

    public AreaConhecimentoService(AreaConhecimentoRepository repo, 
                                   PrimeiraSubAreaRepository primeiraSubAreaRepository,
                                   SegundaSubAreaRepository segundaSubAreaRepository,
                                   TerceiraSubAreaRepository terceiraSubAreaRepository) {
        this.repo = repo;
        this.primeiraSubAreaRepository = primeiraSubAreaRepository;
        this.segundaSubAreaRepository = segundaSubAreaRepository;
        this.terceiraSubAreaRepository = terceiraSubAreaRepository;
    }

    public AreaConhecimento criarArea(String nomeAreaConhecimento) {
        AreaConhecimento area = new AreaConhecimento();
        area.setNomeAreaConhecimento(nomeAreaConhecimento);
        return repo.save(area);
    }

    public boolean adicionarSubarea(AreaConhecimento area, Object subarea) {
        boolean adicionou = false;
        if (subarea instanceof PrimeiraSubArea) {
            area.getPrimeiraSubareas().add((PrimeiraSubArea) subarea);
            adicionou = true;
        } else if (subarea instanceof SegundaSubArea) {
            // Lógica de adicionar Segunda SubÁrea na respectiva coleção.
            adicionou = true;
        } else if (subarea instanceof TerceiraSubArea) {
            // Lógica de adicionar Terceira SubÁrea na respectiva coleção.
            adicionou = true;
        }
        repo.save(area);
        return adicionou; // Retorna true se a subárea foi adicionada, false se não foi.
    }
    

    @Override
    @Cacheable(value = "Areaconhecimentos", condition = "#termoBusca == null or #termoBusca.isBlank()")
    public Page<AreaConhecimento> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()) {
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "Areconhecimento", unless = "#result == null")
    public AreaConhecimento get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "Areaconhecimento", key = "#objeto.id"),
        @CacheEvict(value = "Areaconhecimentos", allEntries = true)
    })
    public AreaConhecimento save(AreaConhecimento objeto) {
        return repo.save(objeto);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "AreaCochecimento", key = "#id"),
        @CacheEvict(value = "AreaConhecimentos", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // Método para buscar PrimeiraSubArea por AreaConhecimento ID
    public List<PrimeiraSubArea> getPrimeiraSubAreasByArea(Long areaId) {
        return primeiraSubAreaRepository.findByAreaConhecimentoId(areaId);
    }

    // Método para buscar SegundaSubArea por PrimeiraSubArea ID
    public List<SegundaSubArea> getSegundaSubAreasByPrimeiraSubArea(Long primeiraSubAreaId) {
        return segundaSubAreaRepository.findByPrimeiraSubAreaId(primeiraSubAreaId);
    }

    // Implementação do método para buscar terceira subárea por segunda subárea
    public List<TerceiraSubArea> getTerceiraSubAreasBySegundaSubArea(Long segundaSubAreaId) {
        SegundaSubArea segundaSubArea = segundaSubAreaRepository.findById(segundaSubAreaId)
            .orElseThrow(() -> new RuntimeException("Segunda Subárea não encontrada"));
        return terceiraSubAreaRepository.findBySegundaSubArea(segundaSubArea);
    }
    

    public AreaConhecimento findById(Long id) {
        Optional<AreaConhecimento> area = repo.findById(id);
        return area.orElse(null);
    }
}