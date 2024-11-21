package fapacapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RubricaEdital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRubricaEdital;

    // @Column
    // private boolean limiteDataRubricaEdital;

    @Column
    private double porcentagemRubricaEdital;

    @Column
    private boolean justificativaRubricaEdital;

    @ManyToOne
    @JoinColumn(name = "idEdital", nullable = false)
    private Edital edital;
    
    @ManyToOne
    @JoinColumn(name = "idRubrica", nullable = false)
    private Rubrica rubrica;

    public Long getIdRubricaEdital() {
        return idRubricaEdital;
    }

    public void setIdRubricaEdital(Long idRubricaEdital) {
        this.idRubricaEdital = idRubricaEdital;
    }

    // public boolean isLimiteDataRubricaEdital() {
    //     return limiteDataRubricaEdital;
    // }

    // public void setLimiteDataRubricaEdital(boolean limiteDataRubricaEdital) {
    //     this.limiteDataRubricaEdital = limiteDataRubricaEdital;
    // }

    public double getPorcentagemRubricaEdital() {
        return porcentagemRubricaEdital;
    }

    public void setPorcentagemRubricaEdital(double porcentagemRubricaEdital) {
        this.porcentagemRubricaEdital = porcentagemRubricaEdital;
    }

    public boolean isJustificativaRubricaEdital() {
        return justificativaRubricaEdital;
    }

    public void setJustificativaRubricaEdital(boolean justificativaRubricaEdital) {
        this.justificativaRubricaEdital = justificativaRubricaEdital;
    }

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }

    public Rubrica getRubrica() {
        return rubrica;
    }

    public void setRubrica(Rubrica rubrica) {
        this.rubrica = rubrica;
    }

    
}
