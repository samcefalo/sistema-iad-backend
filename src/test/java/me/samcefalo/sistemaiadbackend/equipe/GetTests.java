package me.samcefalo.sistemaiadbackend.equipe;

import me.samcefalo.sistemaiadbackend.models.*;
import me.samcefalo.sistemaiadbackend.models.enums.Area;
import me.samcefalo.sistemaiadbackend.models.enums.SituacaoJogo;
import me.samcefalo.sistemaiadbackend.repositories.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

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
    @Autowired
    private TecnicoRepository tecnicoRepository;

    @BeforeAll
    void setUp() {
        JogoFutsal jogoFutsal = new JogoFutsal();
        jogoFutsal.setSituacaoJogo(SituacaoJogo.ENCERRADO.getId());

        Atleta atleta = new Atleta();
        atleta.setNome("Samuel");

        Tecnico tecnico = new Tecnico();
        tecnico.setNome("Zidane");

        jogoFutsal.getAtletas().add(atleta);

        Equipe equipe = new Equipe();
        equipe.setNome("Corinthians");

        atleta.setEquipe(equipe);
        tecnico.setEquipe(equipe);

        jogoFutsal.getEquipes().add(equipe);

        Passe passe = new Passe();
        passe.setAtleta(atleta);
        passe.setEquipe(equipe);
        passe.setGrauDificuldade(1);
        passe.setExito(true);
        passe.setArea(Area.OFENSIVO.getId());
        passe.setJogo(jogoFutsal);

        equipeRepository.save(equipe);
        atletaRepository.save(atleta);
        tecnicoRepository.save(tecnico);
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
