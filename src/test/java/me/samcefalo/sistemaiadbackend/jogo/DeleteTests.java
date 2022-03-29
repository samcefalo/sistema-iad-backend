package me.samcefalo.sistemaiadbackend.jogo;

import me.samcefalo.sistemaiadbackend.domain.JogoFutsal;
import me.samcefalo.sistemaiadbackend.domain.Passe;
import me.samcefalo.sistemaiadbackend.domain.enums.SituacaoJogo;
import me.samcefalo.sistemaiadbackend.services.AcaoService;
import me.samcefalo.sistemaiadbackend.services.JogoFutsalService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(profiles = {"h2"})
public class DeleteTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JogoFutsalService jogoFutsalService;
    @Autowired
    private AcaoService acaoService;

    @BeforeAll
    void setUp() {
        JogoFutsal jogo = new JogoFutsal();
        jogo.setSituacaoJogo(SituacaoJogo.INICIADO.getId());

        Passe passe = new Passe();
        passe.setExito(true);
        passe.setJogo(jogo);

        jogoFutsalService.insert(jogo);
        acaoService.insert(passe);
    }

    @Test
    void case1() throws Exception {
        mockMvc.perform(delete("/jogos/futsal/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void case2() throws Exception {
        mockMvc.perform(get("/jogos/futsal"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void case3() throws Exception {
        mockMvc.perform(get("/acoes"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
