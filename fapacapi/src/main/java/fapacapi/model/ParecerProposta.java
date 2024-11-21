package fapacapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ParecerProposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idParecerProposta;

    @Column(nullable = false)
    private String tituloParecerProposta;

    @Column
    private String descricaoParecerProposta;

    @Column
    private String fileParecerProposta;

    @ManyToOne(optional = false)
    private Proposta proposta;

    public Long getIdParecerProposta() {
        return idParecerProposta;
    }

    public void setIdParecerProposta(Long idParecerProposta) {
        this.idParecerProposta = idParecerProposta;
    }

    public String getTituloParecerProposta() {
        return tituloParecerProposta;
    }

    public void setTituloParecerProposta(String tituloParecerProposta) {
        this.tituloParecerProposta = tituloParecerProposta;
    }

    public String getDescricaoParecerProposta() {
        return descricaoParecerProposta;
    }

    public void setDescricaoParecerProposta(String descricaoParecerProposta) {
        this.descricaoParecerProposta = descricaoParecerProposta;
    }

    public String getFileParecerProposta() {
        return fileParecerProposta;
    }

    public void setFileParecerProposta(String fileParecerProposta) {
        this.fileParecerProposta = fileParecerProposta;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }

}
