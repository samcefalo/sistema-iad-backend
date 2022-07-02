package me.samcefalo.sistemaiadbackend.acao;

import me.samcefalo.sistemaiadbackend.models.Atleta;
import me.samcefalo.sistemaiadbackend.models.Jogo;
import me.samcefalo.sistemaiadbackend.models.Passe;
import me.samcefalo.sistemaiadbackend.models.enums.EsporteEnum;
import me.samcefalo.sistemaiadbackend.services.AcaoService;
import me.samcefalo.sistemaiadbackend.services.AtletaService;
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
    private AtletaService atletaService;
    @Autowired
    private AcaoService acaoService;
    @Autowired
    private JogoService jogoService;

    @BeforeAll
    void setUp() {
        Atleta atleta = new Atleta();
        atleta.setNumero(10);
        atleta.setNome("Samuel");

        Jogo jogoFutsal = new Jogo();
        jogoFutsal.setEsporte(EsporteEnum.FUTSAL.getId());

        Passe passe = new Passe();
        passe.setExito(true);
        passe.setAtleta(atleta);
        passe.setJogo(jogoFutsal);

        atletaService.insert(atleta);
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
