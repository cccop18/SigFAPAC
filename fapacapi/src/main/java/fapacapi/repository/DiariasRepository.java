package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.Diarias;

public interface DiariasRepository extends JpaRepository<Diarias, Long> {
    @Query("SELECT d FROM Diarias d WHERE d.tipoDiaria LIKE %?1%")
    Page<Diarias> busca(String termoBusca, Pageable page);
}