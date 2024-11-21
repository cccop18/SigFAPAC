package fapacapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.UnidadeInstituicao;

public interface UnidadeInstituicaoRepository extends JpaRepository<UnidadeInstituicao, Long>{
    @Query("SELECT u FROM UnidadeInstituicao u  WHERE u.nomeUnidade LIKE %?1%")
    Page<UnidadeInstituicao> busca(String termoBusca, Pageable page);

    List<UnidadeInstituicao> findByInstituicaoIdInstituicao(Long idInstituicao);
}