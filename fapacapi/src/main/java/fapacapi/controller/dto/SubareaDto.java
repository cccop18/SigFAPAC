package fapacapi.controller.dto;


public class SubareaDto {

    private Long id;
    private String tipoSubarea; // Pode ser "primeira", "segunda", "terceira"
    private String nomeSubarea;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoSubarea() {
        return tipoSubarea;
    }

    public void setTipoSubarea(String tipoSubarea) {
        this.tipoSubarea = tipoSubarea;
    }

    public String getNomeSubarea() {
        return nomeSubarea;
    }

    public void setNomeSubarea(String nomeSubarea) {
        this.nomeSubarea = nomeSubarea;
    }
}