package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.DocPessoalEdital;

public interface DocPessoalEditalRepository extends JpaRepository<DocPessoalEdital, Long> {
    @Query("SELECT do FROM DocPessoalEdital do WHERE do.pessoal.nomeDocPessoal LIKE %?1%")
    Page<DocPessoalEdital> busca(String termoBusca, Pageable page);
}