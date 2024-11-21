package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.DiariaProposta;

public interface DiariaPropostaRepository extends JpaRepository<DiariaProposta, Long>{

    @Query("SELECT dp FROM DiariaProposta dp WHERE dp.tipoLocalidadeDiariaProposta LIKE %?1%")
    Page<DiariaProposta> busca(String termoBusca, Pageable page);
    
}
