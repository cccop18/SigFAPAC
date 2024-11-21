package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.Moeda;

public interface MoedaRepository extends JpaRepository<Moeda, Long> {
    @Query("SELECT m FROM Moeda m")
    Page<Moeda> busca(String termoBusca, Pageable page);
}