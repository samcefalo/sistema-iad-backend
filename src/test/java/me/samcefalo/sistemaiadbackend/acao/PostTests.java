package me.samcefalo.sistemaiadbackend.acao;

import me.samcefalo.sistemaiadbackend.domain.Passe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostTests {

    @Autowired
    private MockMvc mockMvc;

    /*
     * POST application/json: Acao.class
     * Expect 201 - Created
     */
    @Test
    void case1() throws Exception {
        Passe passe = new Passe();
        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(passe.toString()))
                .andDo(print())
                .andExpect(status().isCreated());
    }

}
