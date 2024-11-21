package fapacapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InstituicaoTest {
    Instituicao instituicao;

    @BeforeEach
    public void setUp(){
        instituicao = new Instituicao();
    }

    @Test
    public void testInstituicaoId(){
        Long id = 1L;
        instituicao.setIdInstituicao(id);
        assertEquals(id, instituicao.getIdInstituicao());
    }

    @Test
    public void testnomeInstituicao(){
        String nome = "exemplo";
        instituicao.setNomeInstituicao(nome);
        assertEquals(nome, instituicao.getNomeInstituicao());
    }

    @Test
    public void testinstitucaoEstrangeira(){
        Boolean estrangeira = true;
        instituicao.setEstrangeiraInstituicao(estrangeira);
        assertEquals(estrangeira, instituicao.getEstrangeiraInstituicao());
    }


}
