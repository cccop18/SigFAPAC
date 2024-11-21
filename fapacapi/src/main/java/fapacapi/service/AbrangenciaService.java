package fapacapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fapacapi.controller.dto.EditalAbrangenciaDto;
import fapacapi.controller.mapper.EditalAbrangenciaMapper;
import fapacapi.model.Abrangencia;
import fapacapi.model.Edital;
import fapacapi.model.Estado;
import fapacapi.repository.AbrangenciaRepository;
import fapacapi.repository.EditalRepository;
import fapacapi.repository.EstadoRepository;

@Service
public class AbrangenciaService implements IService<Abrangencia> {

    private final AbrangenciaRepository repo;
    private final EditalRepository repoEdital;
    private final EstadoRepository repoEstado;
    private final EditalAbrangenciaMapper mapper;

    public AbrangenciaService(AbrangenciaRepository repo, EditalRepository repoEdital, EstadoRepository repoEstado, EditalAbrangenciaMapper mappper) {
        this.repo = repo;
        this.repoEdital = repoEdital;
        this.repoEstado = repoEstado;
        this.mapper = mappper;
    }

    @Override
    @Cacheable(value = "abrangencias", condition = "#termoBusca == null or #termoBusca.isBlank()")
    public Page<Abrangencia> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()) {
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca, page);
        }
    }

    @Override
    @Cacheable(value = "abrangencia", unless = "#result == null")
    public Abrangencia get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Caching(evict = {
            @CacheEvict(value = "abrangencia", key = "#objeto.id"),
            @CacheEvict(value = "abrangencias", allEntries = true)
    })
     public EditalAbrangenciaDto save(EditalAbrangenciaDto dto) {
        // 1. Converter DTO para a entidade Abrangencia
        Abrangencia abrangencia = mapper.toEntity(dto);

        // 2. Buscar e validar se o Edital existe no banco de dados
        Edital edital = repoEdital.findById(dto.edital())
                .orElseThrow(() -> new IllegalArgumentException("Edital não encontrado para o ID fornecido: " + dto.edital()));

        // 3. Validar se todos os estados fornecidos no DTO existem no banco de dados
        List<Long> estadoIds = dto.estados().stream().map(Estado::getIdEstado).collect(Collectors.toList());
        List<Estado> estados = repoEstado.findByIdEstadoIn(estadoIds);
        if (estados.isEmpty() || estados.size() != estadoIds.size()) {
            throw new IllegalArgumentException("Um ou mais estados fornecidos não foram encontrados no banco de dados.");
        }

        // 4. Associar o Edital e os Estados à Abrangência
        abrangencia.setEdital(edital);
        abrangencia.setEstados(estados);

        // 5. Salvar a Abrangência no banco de dados
        Abrangencia abrangenciaSalva = repo.save(abrangencia);

        // 6. Converter a entidade salva para DTO e retornar
        return mapper.toDto(abrangenciaSalva);
    }
    @Override
    @Caching(evict = {
            @CacheEvict(value = "abrangencia", key = "#objeto.id"),
            @CacheEvict(value = "abrangencias", allEntries = true)
    })
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Abrangencia save(Abrangencia objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}