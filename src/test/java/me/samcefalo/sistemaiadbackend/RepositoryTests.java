package me.samcefalo.sistemaiadbackend;

import me.samcefalo.sistemaiadbackend.domain.*;
import me.samcefalo.sistemaiadbackend.domain.enums.Area;
import me.samcefalo.sistemaiadbackend.domain.enums.SituacaoJogo;
import me.samcefalo.sistemaiadbackend.repositories.AcaoRepository;
import me.samcefalo.sistemaiadbackend.repositories.EquipeRepository;
import me.samcefalo.sistemaiadbackend.repositories.JogadorRepository;
import me.samcefalo.sistemaiadbackend.repositories.JogoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RepositoryTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JogadorRepository jogadorRepository;
    @Autowired
    private JogoRepository jogoRepository;
    @Autowired
    private AcaoRepository acaoRepository;
    @Autowired
    private EquipeRepository equipeRepository;

    @BeforeEach
    void setUp() {
        JogoFutsal jogoFutsal = new JogoFutsal();
        jogoFutsal.setSituacaoJogo(SituacaoJogo.ENCERRADO.getId());

        Jogador jogador = new Jogador();
        jogador.setNome("Samuel");
        jogador.setTitular(true);
        jogador.setExpulso(false);

        jogoFutsal.getJogadores().add(jogador);

        Equipe equipe = new Equipe();
        equipe.setNome("Corinthians");
        equipe.getJogadores().add(jogador);

        jogoFutsal.getEquipes().add(equipe);

        Passe passe = new Passe();
        passe.setJogador(jogador);
        passe.setEquipe(equipe);
        passe.setGrauDificuldade(1);
        passe.setExito(true);
        passe.setArea(Area.OFENSIVO.getId());
        passe.setJogo(jogoFutsal);

        jogadorRepository.save(passe.getJogador());
        equipeRepository.save(passe.getEquipe());
        jogoRepository.save(passe.getJogo());
        acaoRepository.save(passe);
    }

    @Test
    public void case3() {
        List<Acao> acoes = acaoRepository.findAll();
        Acao acao = acoes.get(0);
        assertFalse(acoes.isEmpty());
        assertFalse(acao.getEquipe() == null);
        assertEquals(1, acao.getGrauDificuldade());
        assertEquals(Area.OFENSIVO.getId(), acao.getArea());
    }

    @Transactional
    @Test
    public void case4() {
        List<Jogador> jogadores = jogadorRepository.findAll();
        Jogador jogador = jogadores.get(0);
        List<Acao> acoes = acaoRepository.findByJogador(jogador);
        Acao acao = acoes.get(0);
        assertFalse(jogadores.isEmpty());
        assertFalse(jogador.getJogos().isEmpty());
        assertFalse(acoes.isEmpty());
        assertTrue(acao.getJogador().equals(jogador));
    }

    @Transactional
    @Test
    public void case5() {
        List<Equipe> equipes = equipeRepository.findAll();
        Equipe equipe = equipes.get(0);
        List<Acao> acoes = acaoRepository.findByEquipe(equipe);
        Acao acao = acoes.get(0);
        assertFalse(equipes.isEmpty());
        assertFalse(equipe.getJogos().isEmpty());
        assertFalse(acoes.isEmpty());
        assertTrue(acao.getEquipe().equals(equipe));
    }

    @Test
    void case1() throws Exception {
        mockMvc.perform(get("/jogadores"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void case2() throws Exception {
        mockMvc.perform(get("/equipes"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
