package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.RubricaEdital;

public interface RubricaEditalRepository extends JpaRepository<RubricaEdital, Long>{

    @Query("SELECT rue FROM RubricaEdital rue")
    Page<RubricaEdital> busca(String termoBusca, Pageable page);
    
}
