package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.OrcamentoProposta;

public interface OrcamentoPropostaRepository extends JpaRepository<OrcamentoProposta, Long>{

    @Query("SELECT op FROM OrcamentoProposta op WHERE  op.idOrcamentoProposta = :termoBusca")
    Page<OrcamentoProposta> busca(String termoBusca, Pageable page);
    
}
