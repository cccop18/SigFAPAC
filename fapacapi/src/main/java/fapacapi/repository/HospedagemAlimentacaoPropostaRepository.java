package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.HospedagemAlimentacaoProposta;

public interface HospedagemAlimentacaoPropostaRepository extends JpaRepository<HospedagemAlimentacaoProposta, Long>{
    @Query("SELECT hap FROM HospedagemAlimentacaoProposta hap")
    Page<HospedagemAlimentacaoProposta> busca(String termoBusca, Pageable page);
}