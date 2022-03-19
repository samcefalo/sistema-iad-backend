package me.samcefalo.sistemaiadbackend.acao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GetTests {

    @Autowired
    private MockMvc mockMvc;

    /*
     * /acao
     * GET /acao
     * Expect 200 - OK
     * Retorna uma lista de todas ações
     */
    @Test
    void case1() throws Exception {
        mockMvc.perform(get("/acao"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
