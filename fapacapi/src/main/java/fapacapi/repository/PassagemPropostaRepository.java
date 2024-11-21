package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.PassagemProposta;

public interface PassagemPropostaRepository extends JpaRepository<PassagemProposta, Long>{
    @Query("SELECT ppo FROM PassagemProposta ppo WHERE ppo.tipoPassagemProposta LIKE %?1%")
    Page<PassagemProposta> busca(String termoBusca, Pageable page);
}
