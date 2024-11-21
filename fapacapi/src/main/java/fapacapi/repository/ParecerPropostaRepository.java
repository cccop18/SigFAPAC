package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.ParecerProposta;

public interface ParecerPropostaRepository extends JpaRepository<ParecerProposta, Long>{

    @Query("SELECT ppo FROM ParecerProposta ppo WHERE ppo.tituloParecerProposta LIKE %?1%")
    Page<ParecerProposta> busca(String termoBusca, Pageable page);
    
}
