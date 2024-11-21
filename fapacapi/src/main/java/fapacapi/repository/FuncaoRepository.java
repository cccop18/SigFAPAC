package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.Funcao;

public interface FuncaoRepository extends JpaRepository<Funcao, Long> {
    @Query("SELECT f FROM Funcao f")
    Page<Funcao> busca(String termoBusca, Pageable page);
}