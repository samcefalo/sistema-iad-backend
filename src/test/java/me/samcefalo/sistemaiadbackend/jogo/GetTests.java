package me.samcefalo.sistemaiadbackend.jogo;

import me.samcefalo.sistemaiadbackend.models.Jogo;
import me.samcefalo.sistemaiadbackend.models.enums.EsporteEnum;
import me.samcefalo.sistemaiadbackend.repositories.AcaoRepository;
import me.samcefalo.sistemaiadbackend.repositories.AtletaRepository;
import me.samcefalo.sistemaiadbackend.repositories.EquipeRepository;
import me.samcefalo.sistemaiadbackend.repositories.JogoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(profiles = {"test"})
public class GetTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AcaoRepository acaoRepository;
    @Autowired
    private AtletaRepository atletaRepository;
    @Autowired
    private JogoRepository jogoRepository;
    @Autowired
    private EquipeRepository equipeRepository;

    @BeforeAll
    void setUp() {
        Jogo jogoFutsal = new Jogo();
        jogoFutsal.setEsporte(EsporteEnum.FUTSAL.getId());

        Jogo jogoFutebol = new Jogo();
        jogoFutsal.setEsporte(EsporteEnum.FUTEBOL.getId());

        jogoRepository.saveAll(Arrays.asList(jogoFutsal, jogoFutebol));
    }

    @Test
    public void case1() throws Exception {
        mockMvc.perform(get("/jogos/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void case2() throws Exception {
        mockMvc.perform(get("/jogos/1/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void case3() throws Exception {
        mockMvc.perform(get("/jogos/categoria/futeboL/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
