package fapacapi.controller.dto;

public class UnidadeNomeDto {
    private String nome_unidade;
    private Long id;

    public UnidadeNomeDto(String nome_unidade, Long id) {
        this.nome_unidade = nome_unidade;
        this.id = id;
        
    }

    public String getnome_unidade() {
        return nome_unidade;
    }

    public void setnome_unidade(String nome_unidade) {
        this.nome_unidade = nome_unidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
