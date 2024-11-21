package fapacapi.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fapacapi.model.SegundaSubArea;
import fapacapi.model.TerceiraSubArea;

@Repository
public interface TerceiraSubAreaRepository extends JpaRepository<TerceiraSubArea, Long> {

    // Método de busca por nome da TerceiraSubArea
    @Query("SELECT ta FROM TerceiraSubArea ta WHERE ta.nomeTerceiraSub LIKE %?1%")
    Page<TerceiraSubArea> busca(String termoBusca, Pageable page);

    // Método para buscar TerceiraSubAreas por SegundaSubArea (chave estrangeira)
    List<TerceiraSubArea> findBySegundaSubArea(SegundaSubArea segundaSubArea);


}
