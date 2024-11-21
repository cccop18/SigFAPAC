package fapacapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fapacapi.model.Instituicao;
import fapacapi.repository.InstituicaoRepository;

@ExtendWith(MockitoExtension.class)
public class InstituicaoServiceTest {

    @Mock
    private InstituicaoRepository repo;

    @InjectMocks
    private InstituicaoService servico;

    Instituicao i1;
    Instituicao i2;
    List<Instituicao> instituicoes;

    @BeforeEach
    public void setUp(){
        i1 = new Instituicao();
        i2 = new Instituicao();
        i1.setIdInstituicao(1L);
        i2.setIdInstituicao(2L);
        instituicoes = new ArrayList<>();
        instituicoes.add(i1);
        instituicoes.add(i2);
    }

    @Test
    public void testInstiuicaoDelete(){
        Mockito.doNothing().when(repo).deleteById(1L);
        repo.deleteById(1L);
        Mockito.verify(repo, Mockito.times(1)).deleteById(1L);

        Mockito.doNothing().when(repo).deleteById(2L);
        repo.deleteById(2L);
        Mockito.verify(repo, Mockito.times(1)).deleteById(2L);
    }

    // @Test
    // public void testAtendimentoGetAll() {
    //     Mockito.when(repo.findAll()).thenReturn(instituicoes);
    //     List<Instituicao> result = servico.getAll();
    //     assertEquals(2, result.size());
    //     assertEquals(2L, result.get(1).getId());
    // }


    @Test
    public void testInstituicaoGetById(){
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(i1));
        Instituicao result = servico.get(1L);
        assertEquals(1L, result.getIdInstituicao());
    }
    
}
