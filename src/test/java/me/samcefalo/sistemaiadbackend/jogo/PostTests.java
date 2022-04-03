package me.samcefalo.sistemaiadbackend.jogo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import me.samcefalo.sistemaiadbackend.dtos.EquipeDTO;
import me.samcefalo.sistemaiadbackend.dtos.JogadorDTO;
import me.samcefalo.sistemaiadbackend.dtos.JogoFutsalDTO;
import me.samcefalo.sistemaiadbackend.dtos.PasseDTO;
import me.samcefalo.sistemaiadbackend.models.enums.Area;
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
    void case2() throws Exception {
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
    void case3() throws Exception {
        JogadorDTO jogador = new JogadorDTO();
        jogador.setNome("Samuel");
        jogador.setNumero(10);
        jogador.setEquipeId(1);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(jogador);

        mockMvc.perform(post("/jogadores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void case5() throws Exception {
        JogoFutsalDTO jogoFutsal = new JogoFutsalDTO();
        jogoFutsal.setSituacaoJogo(1);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(jogoFutsal);

        mockMvc.perform(post("/jogos/futsal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void case6() throws Exception {
        PasseDTO passe = new PasseDTO();
        passe.setArea(Area.OFENSIVO.getId());
        passe.setGrauDificuldade(1);
        passe.setJogadorId(1);
        passe.setEquipeId(1);

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

    @Test
    public void case7() throws Exception {
        mockMvc.perform(get("/jogos/futsal/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
