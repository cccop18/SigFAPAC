package fapacapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class VinculoProposta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idVinculoLoProposta;

    @ManyToOne(optional = false)
    private Proposta proposta;

    @ManyToOne(optional = false)
    private Instituicao instituicao;

    @ManyToOne(optional = false)
    private UnidadeInstituicao unidadeInstituicao;

    public Long getIdVinculoLoProposta() {
        return idVinculoLoProposta;
    }

    public void setIdVinculoLoProposta(Long idVinculoLoProposta) {
        this.idVinculoLoProposta = idVinculoLoProposta;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public UnidadeInstituicao getUnidadeInstituicao() {
        return unidadeInstituicao;
    }

    public void setUnidadeInstituicao(UnidadeInstituicao unidadeInstituicao) {
        this.unidadeInstituicao = unidadeInstituicao;
    }
}
