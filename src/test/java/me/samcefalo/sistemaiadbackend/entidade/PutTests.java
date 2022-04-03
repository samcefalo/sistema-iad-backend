package me.samcefalo.sistemaiadbackend.entidade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import me.samcefalo.sistemaiadbackend.dtos.EquipeDTO;
import me.samcefalo.sistemaiadbackend.dtos.JogadorDTO;
import me.samcefalo.sistemaiadbackend.dtos.TecnicoDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(profiles = {"test"})
public class PutTests extends PostTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void case10() throws Exception {
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
    void case11() throws Exception {
        JogadorDTO jogador = new JogadorDTO();
        jogador.setNome("Tadeu");
        jogador.setNumero(20);

        EquipeDTO equipe = new EquipeDTO();
        equipe.setId(2);

        jogador.setEquipe(equipe);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(jogador);

        mockMvc.perform(put("/jogadores/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void case12() throws Exception {
        TecnicoDTO tecnico = new TecnicoDTO();
        tecnico.setNome("Romeu");

        EquipeDTO equipe = new EquipeDTO();
        equipe.setId(2);

        tecnico.setEquipe(equipe);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(tecnico);

        mockMvc.perform(put("/tecnicos/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void case13() throws Exception {
        mockMvc.perform(get("/jogadores"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void case14() throws Exception {
        mockMvc.perform(get("/tecnicos"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
