package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.AcaoDTO;
import me.samcefalo.sistemaiadbackend.models.Acao;
import org.springframework.stereotype.Component;

@Component
public interface AcaoMapper extends AbstractMapper<Acao, AcaoDTO> {

}
