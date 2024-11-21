package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.AbrangenciaProposta;

public interface AbrangenciaPropostaRepository extends JpaRepository<AbrangenciaProposta, Long>{

    @Query("SELECT abp FROM AbrangenciaProposta abp WHERE abp.municipioAbrangenciaProposta LIKE %?1%")
    Page<AbrangenciaProposta> busca(String termoBusca, Pageable page);
    
}
