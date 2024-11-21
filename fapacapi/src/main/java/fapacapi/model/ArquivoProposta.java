package fapacapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ArquivoProposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idArquivoProposta;

    @Column(nullable = false)
    private String tipoArquivoPropostacol;

    @Column(nullable = false)
    private String filePropostaConhecimento;

    @ManyToOne(optional = false)
    private Proposta proposta;

    public Long getIdArquivoProposta() {
        return idArquivoProposta;
    }

    public void setIdArquivoProposta(Long idArquivoProposta) {
        this.idArquivoProposta = idArquivoProposta;
    }

    public String getTipoArquivoPropostacol() {
        return tipoArquivoPropostacol;
    }

    public void setTipoArquivoPropostacol(String tipoArquivoPropostacol) {
        this.tipoArquivoPropostacol = tipoArquivoPropostacol;
    }

    public String getFilePropostaConhecimento() {
        return filePropostaConhecimento;
    }

    public void setFilePropostaConhecimento(String filePropostaConhecimento) {
        this.filePropostaConhecimento = filePropostaConhecimento;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }
    
}
