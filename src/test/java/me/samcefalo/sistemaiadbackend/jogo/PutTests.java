package me.samcefalo.sistemaiadbackend.jogo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import me.samcefalo.sistemaiadbackend.dtos.JogoDTO;
import me.samcefalo.sistemaiadbackend.models.enums.SituacaoJogo;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
        JogoDTO jogoFutsal = new JogoDTO();
        jogoFutsal.setTipoJogo(TipoJogoEnum.FUTSAL.getId());
        jogoFutsal.setSituacaoJogo(SituacaoJogo.ENCERRADO.getId());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(jogoFutsal);

        mockMvc.perform(put("/jogos//2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void case11() throws Exception {
        mockMvc.perform(get("/jogos//"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
