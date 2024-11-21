package fapacapi.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BancoEdital implements Serializable {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idBancoEdital;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "idBanco", nullable = false)
    private Banco banco;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idEdital", nullable = false)
    private Edital edital;

}