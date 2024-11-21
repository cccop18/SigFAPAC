package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.Membros;

public interface MembrosRepository extends JpaRepository<Membros, Long>{

    @Query("SELECT mm FROM Membros mm WHERE mm.proposta.tituloProposta LIKE %?1%")
    Page<Membros> busca(String termoBusca, Pageable page);
    
}
