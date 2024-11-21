package fapacapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    @Query("SELECT a FROM Estado a WHERE a.nomeEstado LIKE %?1%")
    Page<Estado> busca(String termoBusca, Pageable page);

    List<Estado> findByIdEstadoIn(List<Long> ids);

}
