package fapacapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DocPessoalProposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idDocPessoalProposta;

    @Column(nullable = false)
    private String tpoDocPessoalProposta;

    @Column(nullable = false)
    private String fileDocPessoalProposta;

    @ManyToOne(optional = false)
    private Proposta proposta;

    public Long getIdDocPessoalProposta() {
        return idDocPessoalProposta;
    }

    public void setIdDocPessoalProposta(Long idDocPessoalProposta) {
        this.idDocPessoalProposta = idDocPessoalProposta;
    }

    public String getTpoDocPessoalProposta() {
        return tpoDocPessoalProposta;
    }

    public void setTpoDocPessoalProposta(String tpoDocPessoalProposta) {
        this.tpoDocPessoalProposta = tpoDocPessoalProposta;
    }

    public String getFileDocPessoalProposta() {
        return fileDocPessoalProposta;
    }

    public void setFileDocPessoalProposta(String fileDocPessoalProposta) {
        this.fileDocPessoalProposta = fileDocPessoalProposta;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }
    
}
