package fapacapi.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class AtividadeProposta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idAtividadeProposta;

    @Column(nullable = false)
    private String descricaoProposta;

    @Column(nullable = false)
    private float valorAtividdeProposta;

    @Column(nullable = false)
    private String mesInicioAtividadeProposta;

    @Column
    private String duracaoAtividadeProposta;

    @ManyToOne(optional = false)
    private Proposta proposta;

    @ManyToOne(optional = false)
    private Membros membros;

    public Long getIdAtividadeProposta() {
        return idAtividadeProposta;
    }

    public void setIdAtividadeProposta(Long idAtividadeProposta) {
        this.idAtividadeProposta = idAtividadeProposta;
    }

    public String getDescricaoProposta() {
        return descricaoProposta;
    }

    public void setDescricaoProposta(String descricaoProposta) {
        this.descricaoProposta = descricaoProposta;
    }

    public float getValorAtividdeProposta() {
        return valorAtividdeProposta;
    }

    public void setValorAtividdeProposta(float valorAtividdeProposta) {
        this.valorAtividdeProposta = valorAtividdeProposta;
    }

    public String getMesInicioAtividadeProposta() {
        return mesInicioAtividadeProposta;
    }

    public void setMesInicioAtividadeProposta(String mesInicioAtividadeProposta) {
        this.mesInicioAtividadeProposta = mesInicioAtividadeProposta;
    }

    public String getDuracaoAtividadeProposta() {
        return duracaoAtividadeProposta;
    }

    public void setDuracaoAtividadeProposta(String duracaoAtividadeProposta) {
        this.duracaoAtividadeProposta = duracaoAtividadeProposta;
    }

    public Proposta getPropostaConhecimento() {
        return proposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }

    public Membros getMembros() {
        return membros;
    }

    public void setMembros(Membros membros) {
        this.membros = membros;
    }

    
}
