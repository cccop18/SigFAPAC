package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.Edital;

public interface EditalRepository extends JpaRepository<Edital, Long>{
    @Query("SELECT e FROM Edital e WHERE e.tituloEdital LIKE %?1%")
    Page<Edital> busca(String termoBusca, Pageable page);
}