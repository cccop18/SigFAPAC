package fapacapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import fapacapi.model.PrimeiraSubArea;

@Repository
public interface PrimeiraSubAreaRepository extends JpaRepository<PrimeiraSubArea, Long> {

    // Query personalizada para buscar subáreas que contenham o termo de busca
    @Query("SELECT p FROM PrimeiraSubArea p WHERE LOWER(p.nomePrimeiraSub) LIKE LOWER(CONCAT('%', :termoBusca, '%'))")
    Page<PrimeiraSubArea> busca(@Param("termoBusca") String termoBusca, Pageable pageable);

    // Busca todas as PrimeiraSubArea por id de AreaConhecimento usando a FK
    @Query("SELECT p FROM PrimeiraSubArea p WHERE p.areaConhecimento.idAreaConhecimento = :areaConhecimentoId")
    List<PrimeiraSubArea> findByAreaConhecimentoId(@Param("areaConhecimentoId") Long areaConhecimentoId);

}
