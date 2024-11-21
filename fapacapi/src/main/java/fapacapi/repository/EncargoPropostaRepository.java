package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.EncargoProposta;

public interface EncargoPropostaRepository extends JpaRepository<EncargoProposta, Long>{

    @Query("SELECT ep FROM EncargoProposta  ep WHERE ep.especificacaoEncargoProposta LIKE %?1%")
    Page<EncargoProposta> busca(String termoBusca, Pageable page);
    
}
