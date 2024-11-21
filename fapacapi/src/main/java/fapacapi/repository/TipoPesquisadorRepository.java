package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.TipoPesquisador;

public interface TipoPesquisadorRepository extends JpaRepository<TipoPesquisador, Long> {
    @Query("SELECT tp FROM TipoPesquisador tp  WHERE tp.descricaoTipoPesquisador LIKE %?1%")
    Page<TipoPesquisador> busca(String termoBusca, Pageable page);
}