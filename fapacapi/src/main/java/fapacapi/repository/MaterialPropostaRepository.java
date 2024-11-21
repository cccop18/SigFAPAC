package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.MaterialProposta;

public interface MaterialPropostaRepository extends JpaRepository<MaterialProposta, Long>{

    @Query("SELECT mp FROM MaterialProposta mp WHERE mp.medidaMaterialProposta LIKE %?1%")
    Page<MaterialProposta> busca(String termoBusca, Pageable page);
    
}
