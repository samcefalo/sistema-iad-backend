package me.samcefalo.sistemaiadbackend.acao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import me.samcefalo.sistemaiadbackend.dtos.AtletaDTO;
import me.samcefalo.sistemaiadbackend.dtos.EquipeDTO;
import me.samcefalo.sistemaiadbackend.dtos.JogoDTO;
import me.samcefalo.sistemaiadbackend.dtos.PasseDTO;
import me.samcefalo.sistemaiadbackend.models.enums.Area;
import me.samcefalo.sistemaiadbackend.models.enums.TipoJogoEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(profiles = {"test"})
public class PostTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void case1() throws Exception {
        EquipeDTO equipe = new EquipeDTO();
        equipe.setNome("Teste");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(equipe);

        mockMvc.perform(post("/equipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void case0() throws Exception {
        EquipeDTO equipe = new EquipeDTO();
        equipe.setNome("Teste");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(equipe);

        mockMvc.perform(post("/equipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void case2() throws Exception {
        AtletaDTO atleta = new AtletaDTO();
        atleta.setNome("Samuel");
        atleta.setNumero(10);

        EquipeDTO equipe = new EquipeDTO();
        equipe.setId(1);

        atleta.setEquipe(equipe);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(atleta);

        mockMvc.perform(post("/atletas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void case3() throws Exception {
        JogoDTO jogoFutsal = new JogoDTO();
        jogoFutsal.setTipoJogo(TipoJogoEnum.FUTSAL.getId());
        jogoFutsal.setSituacaoJogo(1);

        EquipeDTO equipe = new EquipeDTO();
        equipe.setId(1);

        EquipeDTO equipe2 = new EquipeDTO();
        equipe2.setId(2);

        jogoFutsal.getEquipes().add(equipe);
        jogoFutsal.getEquipes().add(equipe2);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(jogoFutsal);

        mockMvc.perform(post("/jogos/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void case4() throws Exception {
        PasseDTO passe = new PasseDTO();
        passe.setArea(Area.OFENSIVO.getId());
        passe.setGrauDificuldade(1);
        passe.setExito(true);

        EquipeDTO equipe = new EquipeDTO();
        equipe.setId(1);

        AtletaDTO atleta = new AtletaDTO();
        atleta.setId(1);

        JogoDTO jogoFutsal = new JogoDTO();
        jogoFutsal.setTipoJogo(TipoJogoEnum.FUTSAL.getId());
        jogoFutsal.setId(2);

        passe.setAtleta(atleta);
        passe.setEquipe(equipe);
        passe.setJogo(jogoFutsal);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(passe);

        mockMvc.perform(post("/acoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    //UnprocessableEntity - sem atributos necessarios
    @Test
    void case5() throws Exception {
        PasseDTO passe = new PasseDTO();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(passe);

        mockMvc.perform(post("/acoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void case6() throws Exception {
        mockMvc.perform(get("/acoes"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
