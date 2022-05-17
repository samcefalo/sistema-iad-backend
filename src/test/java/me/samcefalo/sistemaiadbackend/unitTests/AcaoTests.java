package me.samcefalo.sistemaiadbackend.unitTests;

import me.samcefalo.sistemaiadbackend.models.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(profiles = {"test"})
public class AcaoTests {

    @Test
    public void verifyPontuacao() {
        Desarme desarme = new Desarme();
        desarme.setExito(true);
        desarme.setGrauDificuldade(3);
        assertEquals(3, desarme.getPontuacao());

        Drible drible = new Drible();
        drible.setExito(true);
        drible.setGrauDificuldade(2);
        assertEquals(2, drible.getPontuacao());

        Finalizacao finalizacao = new Finalizacao();
        finalizacao.setExito(true);
        finalizacao.setGrauDificuldade(4);
        assertEquals(4, finalizacao.getPontuacao());

        Passe passe = new Passe();
        passe.setExito(true);
        passe.setGrauDificuldade(4);
        assertEquals(4, passe.getPontuacao());

        Recepcao recepcao = new Recepcao();
        recepcao.setExito(true);
        recepcao.setGrauDificuldade(4);
        assertEquals(4, recepcao.getPontuacao());
    }

    @Test
    public void verifyMaxPonts() {
        Desarme desarme = new Desarme();
        assertEquals(3, desarme.getMaxPontuacao());

        Drible drible = new Drible();
        assertEquals(2, drible.getMaxPontuacao());

        Finalizacao finalizacao = new Finalizacao();
        assertEquals(4, finalizacao.getMaxPontuacao());

        Passe passe = new Passe();
        assertEquals(4, passe.getMaxPontuacao());

        Recepcao recepcao = new Recepcao();
        assertEquals(4, recepcao.getMaxPontuacao());
    }

}
