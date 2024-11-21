package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.VinculoProposta;

public interface VinculoPropostaRepository extends JpaRepository<VinculoProposta, Long>{

    @Query("SELECT vp FROM VinculoProposta vp WHERE vp.proposta.tituloProposta LIKE %?1%")
    Page<VinculoProposta> busca(String termoBusca, Pageable page);
    
}
