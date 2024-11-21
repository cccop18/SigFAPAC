package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.Instituicao;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
    @Query("SELECT i FROM Instituicao i WHERE i.nomeInstituicao LIKE %?1%")
    Page<Instituicao> busca(String termoBusca, Pageable page);
}
