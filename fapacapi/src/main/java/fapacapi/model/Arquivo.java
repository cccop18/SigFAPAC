package fapacapi.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Arquivo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idArquivo;

    @Column(nullable = false)
    private String nomeArquivo;

    @Column
    private String fileArquivo;

    @Column(nullable = false)
    private boolean ativoArquivo;

    public Long getIdArquivo() {
        return idArquivo;
    }

    public void setIdArquivo(Long idArquivo) {
        this.idArquivo = idArquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getFileArquivo() {
        return fileArquivo;
    }

    public void setFileArquivo(String fileArquivo) {
        this.fileArquivo = fileArquivo;
    }

    public boolean isAtivoArquivo() {
        return ativoArquivo;
    }

    public void setAtivoArquivo(boolean ativoArquivo) {
        this.ativoArquivo = ativoArquivo;
    }
    
}