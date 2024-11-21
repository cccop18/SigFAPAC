package fapacapi.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.EnderecoUnidade;

public interface EnderecoUnidadeRepository extends JpaRepository<EnderecoUnidade, Long>{
    @Query("SELECT u FROM EnderecoUnidade u WHERE u.cepEnderecoUnidade LIKE %?1%")
    Page<EnderecoUnidade> busca(String termoBusca, Pageable page);
}
