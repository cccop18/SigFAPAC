    package fapacapi.model;

    import jakarta.persistence.*;
    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.List;

    @Entity
    public class Abrangencia implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(nullable = false, updatable = false)
        private Long idAbrangencia;

        @ManyToOne(optional = false)
        @JoinColumn(name = "idEdital", nullable = false)
        private Edital edital;  // Relacionamento com Edital

        @ManyToMany  // Relacionamento muitos-para-muitos com Estado
        @JoinTable(
            name = "abrangencia_estado",  // Nome da tabela intermediária
            joinColumns = @JoinColumn(name = "idAbrangencia"),  // Chave estrangeira para Abrangencia
            inverseJoinColumns = @JoinColumn(name = "idEstado")  // Chave estrangeira para Estado
        )
        private List<Estado> estados = new ArrayList<>();  // Lista de Estados associados à Abrangência

        // Getters e Setters
        public Long getIdAbrangencia() {
            return idAbrangencia;
        }

        public void setIdAbrangencia(Long idAbrangencia) {
            this.idAbrangencia = idAbrangencia;
        }

        public Edital getEdital() {
            return edital;
        }

        public void setEdital(Edital edital) {
            this.edital = edital;
        }

        public List<Estado> getEstados() {
            return estados;
        }

        public void setEstados(List<Estado> estados) {
            this.estados = estados;
        }
    }
