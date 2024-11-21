package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.BancoEdital;

public interface BancoEditalRepository extends JpaRepository<BancoEdital, Long> {
    @Query("SELECT be FROM BancoEdital be WHERE be.banco.nomeBanco LIKE %?1%")
    Page<BancoEdital> busca(String termoBusca, Pageable page);
}