package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.Pesquisador;

public interface PesquisadorRepository extends JpaRepository<Pesquisador, Long> {
    @Query("SELECT p FROM Pesquisador p WHERE p.nomeCompletoPesquisador LIKE %?1%")
    Page<Pesquisador> busca(String termoBusca, Pageable page);

}
