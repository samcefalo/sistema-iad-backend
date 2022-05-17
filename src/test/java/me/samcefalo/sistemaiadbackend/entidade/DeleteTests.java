package me.samcefalo.sistemaiadbackend.entidade;

import me.samcefalo.sistemaiadbackend.models.Atleta;
import me.samcefalo.sistemaiadbackend.models.Passe;
import me.samcefalo.sistemaiadbackend.services.AcaoService;
import me.samcefalo.sistemaiadbackend.services.AtletaService;
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
@ActiveProfiles(profiles = {"test"})
public class DeleteTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AtletaService atletaService;
    @Autowired
    private AcaoService acaoService;

    @BeforeAll
    void setUp() {
        Atleta atleta = new Atleta();
        atleta.setNumero(10);
        atleta.setNome("Samuel");

        Passe passe = new Passe();
        passe.setExito(true);
        passe.setAtleta(atleta);

        atletaService.insert(atleta);
        acaoService.insert(passe);
    }

    @Test
    void case1() throws Exception {
        mockMvc.perform(delete("/atletas/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void case2() throws Exception {
        mockMvc.perform(get("/atletas"))
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
