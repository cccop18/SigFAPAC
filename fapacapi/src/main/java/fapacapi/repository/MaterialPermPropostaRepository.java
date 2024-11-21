package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.MaterialPermProposta;

public interface MaterialPermPropostaRepository extends JpaRepository<MaterialPermProposta, Long>{
    @Query("SELECT mpp FROM MaterialPermProposta mpp WHERE mpp.especificacaoMaterialPermProposta LIKE %?1%")
    Page<MaterialPermProposta> busca(String termoBusca, Pageable page);
}
