package me.samcefalo.sistemaiadbackend.mappers.implementations;

import me.samcefalo.sistemaiadbackend.dtos.JogoDTO;
import me.samcefalo.sistemaiadbackend.mappers.JogoMapper;
import me.samcefalo.sistemaiadbackend.models.Jogo;
import org.springframework.stereotype.Component;

@Component
public class JogoMapperImpl extends AbstractMapperImpl<Jogo, JogoDTO> implements JogoMapper {

    public JogoMapperImpl() {
        super.configure(Jogo.class, JogoDTO.class);
    }

}
