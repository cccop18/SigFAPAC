package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.FuncaoEdital;

public interface FuncaoEditalRepository extends JpaRepository<FuncaoEdital, Long> {
    @Query("SELECT fe FROM FuncaoEdital fe")
    Page<FuncaoEdital> busca(String termoBusca, Pageable page);
}