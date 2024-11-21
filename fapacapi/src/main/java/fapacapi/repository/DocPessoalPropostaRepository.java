package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.DocPessoalProposta;

public interface DocPessoalPropostaRepository extends JpaRepository<DocPessoalProposta, Long>{

    @Query("SELECT docp FROM DocPessoalProposta docp WHERE docp.tpoDocPessoalProposta LIKE %?1%")
    Page<DocPessoalProposta> busca(String termoBusca, Pageable page);
    
}
