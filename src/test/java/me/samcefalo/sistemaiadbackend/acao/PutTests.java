package me.samcefalo.sistemaiadbackend.acao;

import me.samcefalo.sistemaiadbackend.domain.Jogador;
import me.samcefalo.sistemaiadbackend.domain.Passe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PutTests {

    @Autowired
    private MockMvc mockMvc;

    /*
     * /acao/{id}
     * PUT JSON /acao/1
     * Expect 204 - No Content
     * PUT com todos elementos
     */
    @Test
    void case1() throws Exception {
        Passe passe = new Passe();
        passe.setId(1);
        passe.setJogador(new Jogador());
        mockMvc.perform(put("/acao/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(passe.toString()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}
