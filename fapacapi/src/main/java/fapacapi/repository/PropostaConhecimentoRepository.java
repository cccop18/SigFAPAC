package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.PropostaConhecimento;

public interface PropostaConhecimentoRepository extends JpaRepository<PropostaConhecimento, Long>{

    @Query("SELECT pc FROM PropostaConhecimento pc WHERE pc.areaConhecimento.nomeAreaConhecimento LIKE %?1% ")
    Page<PropostaConhecimento> busca(String termoBusca, Pageable page);
    
}
