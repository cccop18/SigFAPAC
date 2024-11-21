package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.Proposta;

public interface PropostaRepostory extends JpaRepository<Proposta, Long>{

    @Query("SELECT pro FROM Proposta pro WHERE pro.tituloProposta LIKE %?1%")
    Page<Proposta> busca(String termoBusca, Pageable page);
    
}
