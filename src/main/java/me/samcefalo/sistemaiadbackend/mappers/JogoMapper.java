package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.JogoDTO;
import me.samcefalo.sistemaiadbackend.models.Jogo;
import org.springframework.stereotype.Component;

@Component
public interface JogoMapper extends AbstractMapper<Jogo, JogoDTO> {

}
