package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.ArquivoEdital;

public interface ArquivoEditalRepository extends JpaRepository<ArquivoEdital, Long> {
    @Query("SELECT ae FROM ArquivoEdital ae")
    Page<ArquivoEdital> busca(String termoBusca, Pageable page);
}