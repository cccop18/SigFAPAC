package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.AtividadeProposta;

public interface AtividadePropostaRepository extends JpaRepository<AtividadeProposta, Long>{

    @Query("SELECT ap FROM AtividadeProposta ap WHERE ap.mesInicioAtividadeProposta LIKE %?1%")
    Page<AtividadeProposta> busca(String termoBusca, Pageable page);
    
}
