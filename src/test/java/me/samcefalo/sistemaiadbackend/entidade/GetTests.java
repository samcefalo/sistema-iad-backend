package me.samcefalo.sistemaiadbackend.entidade;

import me.samcefalo.sistemaiadbackend.models.Atleta;
import me.samcefalo.sistemaiadbackend.models.Equipe;
import me.samcefalo.sistemaiadbackend.models.Jogo;
import me.samcefalo.sistemaiadbackend.models.Passe;
import me.samcefalo.sistemaiadbackend.models.enums.Area;
import me.samcefalo.sistemaiadbackend.models.enums.EsporteEnum;
import me.samcefalo.sistemaiadbackend.repositories.AcaoRepository;
import me.samcefalo.sistemaiadbackend.repositories.AtletaRepository;
import me.samcefalo.sistemaiadbackend.repositories.EquipeRepository;
import me.samcefalo.sistemaiadbackend.repositories.JogoRepository;
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

        Atleta atleta = new Atleta();
        atleta.setNome("Samuel");

        jogoFutsal.getAtletas().add(atleta);

        Equipe equipe = new Equipe();
        equipe.setNome("Corinthians");

        atleta.setEquipe(equipe);

        jogoFutsal.getEquipes().add(equipe);

        Passe passe = new Passe();
        passe.setAtleta(atleta);
        passe.setEquipe(equipe);
        passe.setGrauDificuldade(1);
        passe.setExito(false);
        passe.setArea(Area.OFENSIVO.getId());
        passe.setJogo(jogoFutsal);

        equipeRepository.save(equipe);
        atletaRepository.save(atleta);
        jogoRepository.save(jogoFutsal);
        acaoRepository.save(passe);
    }

    @Test
    public void case1() throws Exception {
        mockMvc.perform(get("/atletas"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void case2() throws Exception {
        mockMvc.perform(get("/atletas/1/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void case3() throws Exception {
        mockMvc.perform(get("/atletas/1/jogos"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void case4() throws Exception {
        mockMvc.perform(get("/atletas/1/acoes"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void case5() throws Exception {
        mockMvc.perform(get("/tecnicos/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void case6() throws Exception {
        mockMvc.perform(get("/tecnicos/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
