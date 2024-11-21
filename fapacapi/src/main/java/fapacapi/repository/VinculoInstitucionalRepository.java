package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.VinculoInstitucional;

public interface VinculoInstitucionalRepository extends JpaRepository<VinculoInstitucional, Long> {
    @Query("SELECT v FROM VinculoInstitucional v WHERE v.vinculoInstitucional LIKE %?1%")
    Page<VinculoInstitucional> busca(String termoBusca, Pageable page);
}
