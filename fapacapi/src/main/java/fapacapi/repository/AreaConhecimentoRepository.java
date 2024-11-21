package fapacapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.AreaConhecimento;

public interface AreaConhecimentoRepository  extends JpaRepository<AreaConhecimento, Long> {
    @Query("SELECT ac FROM AreaConhecimento ac  WHERE ac.nomeAreaConhecimento LIKE %?1%")
    Page<AreaConhecimento> busca(String termoBusca, Pageable page);
}