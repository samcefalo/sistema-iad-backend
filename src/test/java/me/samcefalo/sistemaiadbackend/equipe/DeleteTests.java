package me.samcefalo.sistemaiadbackend.equipe;

import me.samcefalo.sistemaiadbackend.domain.Equipe;
import me.samcefalo.sistemaiadbackend.domain.Jogador;
import me.samcefalo.sistemaiadbackend.services.EquipeService;
import me.samcefalo.sistemaiadbackend.services.JogadorService;
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
    private JogadorService jogadorService;
    @Autowired
    private EquipeService equipeService;

    @BeforeAll
    void setUp() {
        Jogador jogador = new Jogador();
        jogador.setExpulso(false);
        jogador.setTitular(true);
        jogador.setNumero(10);
        jogador.setNome("Samuel");

        Equipe equipe = new Equipe();
        equipe.setNome("Teste");

        jogador.setEquipe(equipe);

        equipeService.insert(equipe);
        jogadorService.insert(jogador);
    }

    @Test
    void case1() throws Exception {
        mockMvc.perform(delete("/equipes/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void case2() throws Exception {
        mockMvc.perform(get("/jogadores"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void case3() throws Exception {
        mockMvc.perform(get("/equipes"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
