package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.EquipeDTO;
import me.samcefalo.sistemaiadbackend.models.Equipe;
import org.springframework.stereotype.Component;

@Component
public class EquipeMapper extends MapperImpl<Equipe, EquipeDTO> {

    public EquipeMapper() {
        super.configure(Equipe.class, EquipeDTO.class);
    }

}
