package me.samcefalo.sistemaiadbackend.equipe;

import me.samcefalo.sistemaiadbackend.domain.*;
import me.samcefalo.sistemaiadbackend.domain.enums.Area;
import me.samcefalo.sistemaiadbackend.domain.enums.SituacaoJogo;
import me.samcefalo.sistemaiadbackend.repositories.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AcaoRepository acaoRepository;
    @Autowired
    private JogadorRepository jogadorRepository;
    @Autowired
    private JogoRepository jogoRepository;
    @Autowired
    private EquipeRepository equipeRepository;
    @Autowired
    private TecnicoRepository tecnicoRepository;

    @BeforeAll
    void setUp() {
        JogoFutsal jogoFutsal = new JogoFutsal();
        jogoFutsal.setSituacaoJogo(SituacaoJogo.ENCERRADO.getId());

        Jogador jogador = new Jogador();
        jogador.setNome("Samuel");
        jogador.setTitular(true);
        jogador.setExpulso(false);

        Tecnico tecnico = new Tecnico();
        tecnico.setNome("Zidane");

        jogoFutsal.getJogadores().add(jogador);

        Equipe equipe = new Equipe();
        equipe.setNome("Corinthians");
        equipe.getJogadores().add(jogador);
        equipe.setTecnico(tecnico);

        jogoFutsal.getEquipes().add(equipe);

        Passe passe = new Passe();
        passe.setJogador(jogador);
        passe.setEquipe(equipe);
        passe.setGrauDificuldade(1);
        passe.setExito(true);
        passe.setArea(Area.OFENSIVO.getId());
        passe.setJogo(jogoFutsal);

        jogadorRepository.save(jogador);
        tecnicoRepository.save(tecnico);
        equipeRepository.save(equipe);
        jogoRepository.save(jogoFutsal);
        acaoRepository.save(passe);
    }

    @Test
    public void case1() throws Exception {
        mockMvc.perform(get("/equipes"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void case2() throws Exception {
        mockMvc.perform(get("/equipes/1/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void case3() throws Exception {
        mockMvc.perform(get("/equipes/1/jogos"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void case4() throws Exception {
        mockMvc.perform(get("/equipes/1/acoes"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
