package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import fapacapi.model.ArquivoProposta;

public interface ArquivoPropostaRepository extends JpaRepository<ArquivoProposta, Long>{

    @Query("SELECT ap FROM ArquivoProposta ap WHERE ap.tipoArquivoPropostacol LIKE %?1%")
    Page<ArquivoProposta> busca(String termoBusca, Pageable page);
    
}
