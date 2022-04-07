package me.samcefalo.sistemaiadbackend.mappers.implementations;

import me.samcefalo.sistemaiadbackend.dtos.AcaoDTO;
import me.samcefalo.sistemaiadbackend.dtos.JogoDTO;
import me.samcefalo.sistemaiadbackend.mappers.AcaoMapper;
import me.samcefalo.sistemaiadbackend.models.Acao;
import me.samcefalo.sistemaiadbackend.models.Jogo;
import org.springframework.stereotype.Component;

@Component
public class AcaoMapperImpl extends AbstractMapperImpl<Acao, AcaoDTO> implements AcaoMapper {

    public AcaoMapperImpl() {
        super.configure(Jogo.class, JogoDTO.class);
        super.configure(Acao.class, AcaoDTO.class);
    }

}
