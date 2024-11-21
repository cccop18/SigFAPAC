package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.Retificacoes;

public interface RetificacoesRepository extends JpaRepository<Retificacoes, Long>{
    @Query("SELECT re FROM  Retificacoes re WHERE re.nomeRetificacoes LIKE %?1%")
    Page<Retificacoes> busca(String termoBusca, Pageable page);
}