package me.samcefalo.sistemaiadbackend.entidade;

import me.samcefalo.sistemaiadbackend.models.Atleta;
import me.samcefalo.sistemaiadbackend.repositories.AtletaRepository;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(profiles = {"test"})
public class UnitTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AtletaRepository atletaRepository;

    @BeforeAll
    void setUp() {
        Atleta atleta = new Atleta();
        atleta.setNumero(10);
        atleta.setNome("Samuel");

        atletaRepository.save(atleta);
    }

    @Test
    public void case1() {
        List<Atleta> atletas = atletaRepository.findAll();
        Atleta atleta = atletas.get(0);
        assertFalse(atletas.isEmpty());
        assertEquals("Samuel", atleta.getNome());
    }

    @Test
    void case2() throws Exception {
        mockMvc.perform(get("/atletas"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
