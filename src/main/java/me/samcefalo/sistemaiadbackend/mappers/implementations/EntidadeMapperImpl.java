package me.samcefalo.sistemaiadbackend.mappers.implementations;

import me.samcefalo.sistemaiadbackend.dtos.EntidadeDTO;
import me.samcefalo.sistemaiadbackend.mappers.EntidadeMapper;
import me.samcefalo.sistemaiadbackend.models.Entidade;
import org.springframework.stereotype.Component;

@Component
public class EntidadeMapperImpl extends AbstractMapperImpl<Entidade, EntidadeDTO> implements EntidadeMapper {

    public EntidadeMapperImpl() {
        super.configure(Entidade.class, EntidadeDTO.class);
    }

}
