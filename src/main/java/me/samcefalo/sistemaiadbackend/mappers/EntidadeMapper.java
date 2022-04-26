package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.EntidadeDTO;
import me.samcefalo.sistemaiadbackend.models.Entidade;
import org.springframework.stereotype.Component;

@Component
public class EntidadeMapper extends MapperImpl<Entidade, EntidadeDTO> {

    public EntidadeMapper() {
        super.configure(Entidade.class, EntidadeDTO.class);
    }

}
