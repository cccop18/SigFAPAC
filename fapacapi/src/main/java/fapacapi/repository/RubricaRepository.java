package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.Rubrica;

public interface RubricaRepository extends JpaRepository<Rubrica, Long>{
    @Query("SELECT r FROM Rubrica r")
    Page<Rubrica> busca(String termoBusca, Pageable page);
}
