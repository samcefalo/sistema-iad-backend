package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.EquipeDTO;
import me.samcefalo.sistemaiadbackend.models.Equipe;
import org.springframework.stereotype.Component;

@Component
public interface EquipeMapper extends AbstractMapper<Equipe, EquipeDTO> {

}
