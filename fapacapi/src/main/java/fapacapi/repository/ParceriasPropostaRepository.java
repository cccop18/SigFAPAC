package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.ParceriasProposta;

public interface ParceriasPropostaRepository extends JpaRepository<ParceriasProposta, Long>{
    
    @Query("SELECT pp FROM ParceriasProposta pp WHERE pp.entidadeParceriaProposta LIKE %?1%")
    Page<ParceriasProposta> busca(String termoBusca, Pageable page);
}
