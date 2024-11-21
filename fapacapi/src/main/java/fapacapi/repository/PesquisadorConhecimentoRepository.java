package fapacapi.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fapacapi.model.PesquisadorConhecimento;

public interface PesquisadorConhecimentoRepository extends JpaRepository<PesquisadorConhecimento, Long> {
    @Query("SELECT pc FROM PesquisadorConhecimento pc")
    Page<PesquisadorConhecimento> busca(String termoBusca, Pageable pageable);
}
