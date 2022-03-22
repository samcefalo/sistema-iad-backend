package me.samcefalo.sistemaiadbackend.acao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PostTests {

    @Autowired
    private MockMvc mockMvc;

    /*
     * POST application/json: Acao.class
     * Expect 201 - Created

    @Test
    void case1() throws Exception {
        Passe passe = new Passe();
        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(passe.toString()))
                .andDo(print())
                .andExpect(status().isCreated());
    }
     */

}
