package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.PessoalProposta;

public interface PessoalPropostaRepository extends JpaRepository<PessoalProposta, Long>{
    @Query("SELECT pep FROM PessoalProposta pep WHERE pep.funcaoPessoalProposta LIKE %?1%")
    Page<PessoalProposta> busca(String termoBusca, Pageable page);
    
}
