package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.AtletaDTO;
import me.samcefalo.sistemaiadbackend.models.Atleta;
import org.springframework.stereotype.Component;

@Component
public class AtletaMapper extends MapperImpl<Atleta, AtletaDTO> {

    public AtletaMapper() {
        super.configure(Atleta.class, AtletaDTO.class);
    }

}
