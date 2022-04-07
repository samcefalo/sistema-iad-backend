package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.EntidadeDTO;
import me.samcefalo.sistemaiadbackend.models.Entidade;
import org.springframework.stereotype.Component;

@Component
public interface EntidadeMapper extends AbstractMapper<Entidade, EntidadeDTO> {

}
