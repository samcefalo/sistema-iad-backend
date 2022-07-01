package me.samcefalo.sistemaiadbackend;

import me.samcefalo.sistemaiadbackend.models.*;
import me.samcefalo.sistemaiadbackend.models.enums.Area;
import me.samcefalo.sistemaiadbackend.models.enums.SituacaoJogo;
import me.samcefalo.sistemaiadbackend.models.enums.TipoJogoEnum;
import me.samcefalo.sistemaiadbackend.services.AcaoService;
import me.samcefalo.sistemaiadbackend.services.AtletaService;
import me.samcefalo.sistemaiadbackend.services.EquipeService;
import me.samcefalo.sistemaiadbackend.services.JogoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(profiles = {"test"})
public class RepositoryTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AtletaService atletaService;
    @Autowired
    private JogoService jogoService;
    @Autowired
    private AcaoService acaoService;
    @Autowired
    private EquipeService equipeService;

    /*
    A classe mais dependente (filho) sempre adiciona a classe pai
     */

    @BeforeAll
    void setUp() {
        Jogo jogoFutsal = new Jogo();
        jogoFutsal.setTipoJogo(TipoJogoEnum.FUTSAL.getId());
        jogoFutsal.setSituacaoJogo(SituacaoJogo.ENCERRADO.getId());

        Atleta atleta = new Atleta();
        atleta.setNumero(10);
        atleta.setNome("Samuel");
        //atleta.getJogos().add(jogoFutsal);

        jogoFutsal.getAtletas().add(atleta);

        Equipe equipe = new Equipe();
        equipe.setNome("Corinthians");

        atleta.setEquipe(equipe);

        jogoFutsal.getEquipes().add(equipe);

        Passe passe = new Passe();
        passe.setAtleta(atleta);
        passe.setEquipe(equipe);
        passe.setGrauDificuldade(1);
        passe.setExito(true);
        passe.setArea(Area.OFENSIVO.getId());
        passe.setJogo(jogoFutsal);

        equipeService.insert(equipe);
        atletaService.insert(atleta);
        jogoService.insert(jogoFutsal);
        acaoService.insert(passe);
    }

    @Test
    void case1() throws Exception {
        mockMvc.perform(get("/atletas"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void case2() throws Exception {
        mockMvc.perform(get("/equipes"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void case8() throws Exception {
        mockMvc.perform(get("/jogos/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void case3() {
        List<Acao> acoes = acaoService.findAll();
        Acao acao = acoes.get(0);
        assertFalse(acoes.isEmpty());
        assertFalse(acao.getEquipe() == null);
        assertEquals(1, acao.getGrauDificuldade());
        assertEquals(Area.OFENSIVO.getId(), acao.getArea());
    }

    @Transactional
    @Test
    public void case4() {
        List<Atleta> atletas = atletaService.findAll();
        Atleta atleta = atletas.get(0);
        List<Acao> acoes = acaoService.findByAtleta(atleta);
        Acao acao = acoes.get(0);
        assertFalse(atletas.isEmpty());
        assertFalse(atleta.getJogos().isEmpty());
        assertFalse(acoes.isEmpty());
        assertTrue(acao.getAtleta().equals(atleta));
    }

    @Transactional
    @Test
    public void case5() {
        List<Equipe> equipes = equipeService.findAll();
        Equipe equipe = equipes.get(0);
        List<Acao> acoes = acaoService.findByEquipe(equipe);
        Acao acao = acoes.get(0);
        assertFalse(equipes.isEmpty());
        assertFalse(equipe.getJogos().isEmpty());
        assertFalse(acoes.isEmpty());
        assertTrue(acao.getEquipe().equals(equipe));
    }

    @Transactional
    @Test
    public void case6() {
        List<Atleta> atletas = atletaService.findAll();
        Atleta atleta = atletas.get(0);
        List<Jogo> jogos = jogoService.findByAtletas(atleta);
        Jogo jogo = jogos.get(0);
        assertFalse(jogos.isEmpty());
        assertFalse(jogo.getAtletas().isEmpty());
        assertEquals(SituacaoJogo.ENCERRADO.getId(), jogo.getSituacaoJogo());
        assertTrue(jogo.getAtletas().contains(atleta));
    }

}
