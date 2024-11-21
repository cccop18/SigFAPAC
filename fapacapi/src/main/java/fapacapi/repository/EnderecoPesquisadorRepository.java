package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.EnderecoPesquisador;

public interface EnderecoPesquisadorRepository extends JpaRepository<EnderecoPesquisador, Long> {
    @Query(
        "SELECT endPesq FROM EnderecoPesquisador endPesq " +
        "LEFT JOIN endPesq.pesquisador p " +
        "WHERE p.nomeCompletoPesquisador LIKE %?1%"
    )
    Page<EnderecoPesquisador> busca(String termoBusca, Pageable page);
}
