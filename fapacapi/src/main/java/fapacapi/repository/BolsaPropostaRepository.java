package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.BolsaProposta;

public interface BolsaPropostaRepository extends JpaRepository<BolsaProposta, Long>{

    @Query("SELECT bp FROM BolsaProposta bp WHERE bp.modalidadeBolsaProposta LIKE %?1%")
    Page<BolsaProposta> busca(String termoBusca, Pageable page);
    
}
