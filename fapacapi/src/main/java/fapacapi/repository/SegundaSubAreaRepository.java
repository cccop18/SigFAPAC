package fapacapi.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fapacapi.model.SegundaSubArea;

@Repository
public interface SegundaSubAreaRepository extends JpaRepository<SegundaSubArea, Long> {
    
    @Query("SELECT sa FROM SegundaSubArea sa WHERE sa.nomeSegundaSub LIKE %?1%")
    Page<SegundaSubArea> busca(String termoBusca, Pageable page);

    List<SegundaSubArea> findByPrimeiraSubAreaId(Long primeiraSubAreaId);
}
