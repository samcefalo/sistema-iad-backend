package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.JogoDTO;
import me.samcefalo.sistemaiadbackend.models.Jogo;
import org.springframework.stereotype.Component;

@Component
public class JogoMapper extends MapperImpl<Jogo, JogoDTO> {

    public JogoMapper() {
        super.configure(Jogo.class, JogoDTO.class);
    }

}
