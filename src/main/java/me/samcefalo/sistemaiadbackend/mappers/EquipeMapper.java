package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.EquipeDTO;
import me.samcefalo.sistemaiadbackend.models.Equipe;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EquipeMapper {

    private ModelMapper modelMapper;

    public EquipeMapper() {
        modelMapper = new ModelMapper();
    }

    public Equipe mapTo(EquipeDTO equipeDTO) {
        return this.modelMapper.map(equipeDTO, Equipe.class);
    }

    public EquipeDTO mapTo(Equipe equipe) {
        return this.modelMapper.map(equipe, EquipeDTO.class);
    }

}
