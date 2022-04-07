package me.samcefalo.sistemaiadbackend.mappers.implementations;

import me.samcefalo.sistemaiadbackend.dtos.EquipeDTO;
import me.samcefalo.sistemaiadbackend.mappers.EquipeMapper;
import me.samcefalo.sistemaiadbackend.models.Equipe;
import org.springframework.stereotype.Component;

@Component
public class EquipeMapperImpl extends AbstractMapperImpl<Equipe, EquipeDTO> implements EquipeMapper {

    public EquipeMapperImpl() {
        super.configure(Equipe.class, EquipeDTO.class);
    }

}
