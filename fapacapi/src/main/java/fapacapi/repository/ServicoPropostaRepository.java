package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.ServicoProposta;

public interface ServicoPropostaRepository extends JpaRepository<ServicoProposta, Long>{
    @Query("SELECT ser FROM ServicoProposta ser WHERE ser.tipoServicoProposta LIKE %?1%")
    Page<ServicoProposta> busca(String termoBusca, Pageable page);
}
