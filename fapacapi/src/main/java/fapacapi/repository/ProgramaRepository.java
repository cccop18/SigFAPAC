package fapacapi.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.Programa;

public interface ProgramaRepository extends JpaRepository<Programa, Long> {
    @Query("SELECT pro FROM Programa pro WHERE pro.nomePrograma LIKE %?1%")
    Page<Programa> busca(String termoBusca, Pageable page);
}