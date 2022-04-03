package me.samcefalo.sistemaiadbackend.acao;

import me.samcefalo.sistemaiadbackend.models.Jogador;
import me.samcefalo.sistemaiadbackend.models.JogoFutsal;
import me.samcefalo.sistemaiadbackend.models.Passe;
import me.samcefalo.sistemaiadbackend.services.AcaoService;
import me.samcefalo.sistemaiadbackend.services.JogadorService;
import me.samcefalo.sistemaiadbackend.services.JogoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(profiles = {"test"})
public class GetTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JogadorService jogadorService;
    @Autowired
    private AcaoService acaoService;
    @Autowired
    private JogoService jogoService;

    @BeforeAll
    void setUp() {
        Jogador jogador = new Jogador();
        jogador.setExpulso(false);
        jogador.setTitular(true);
        jogador.setNumero(10);
        jogador.setNome("Samuel");

        JogoFutsal jogoFutsal = new JogoFutsal();
        jogoFutsal.setSituacaoJogo(1);

        Passe passe = new Passe();
        passe.setExito(true);
        passe.setJogador(jogador);
        passe.setJogo(jogoFutsal);

        jogadorService.insert(jogador);
        jogoService.insert(jogoFutsal);
        acaoService.insert(passe);
    }

    @Test
    public void case1() throws Exception {
        mockMvc.perform(get("/acoes"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void case2() throws Exception {
        mockMvc.perform(get("/acoes/3"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
