package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.Banco;

public interface BancoRepository extends JpaRepository<Banco, Long> {
    @Query("SELECT b FROM Banco b WHERE b.nomeBanco LIKE %?1%")
    Page<Banco> busca(String termoBusca, Pageable page);
}