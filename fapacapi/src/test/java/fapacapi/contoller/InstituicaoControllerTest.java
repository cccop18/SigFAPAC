package fapacapi.contoller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fapacapi.model.Instituicao;
import fapacapi.service.InstituicaoService;

@WebMvcTest(InstituicaoControllerTest.class)
public class InstituicaoControllerTest {

    @MockBean
    private InstituicaoService service;

    @SuppressWarnings("unused")
    @Autowired
    private MockMvc mockMvc;

    Instituicao i1, i2;
    List<Instituicao> instituicoes;
    String jsonContent;

    @BeforeEach
    public void setUp() throws JsonProcessingException{
        i1 = new Instituicao();
        i2 = new Instituicao();
        i1.setIdInstituicao(1L);
        i2.setIdInstituicao(2L);
        jsonContent = new ObjectMapper().writeValueAsString(i1);
        instituicoes = new ArrayList<Instituicao>();
        instituicoes.add(i1);
        instituicoes.add(i2);
    }
    
}
