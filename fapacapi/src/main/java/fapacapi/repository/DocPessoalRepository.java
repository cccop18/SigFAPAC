package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.DocPessoal;

public interface DocPessoalRepository  extends JpaRepository<DocPessoal, Long>{
    @Query("SELECT d FROM DocPessoal d WHERE d.nomeDocPessoal LIKE %?1%")
    Page<DocPessoal> busca(String termoBusca, Pageable page);
}
