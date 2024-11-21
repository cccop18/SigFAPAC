package fapacapi.repository;

import fapacapi.model.Abrangencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AbrangenciaRepository extends JpaRepository<Abrangencia, Long> {
     @Query("SELECT ab FROM Abrangencia ab")
    Page<Abrangencia> busca(String termoBusca, Pageable page);
}
