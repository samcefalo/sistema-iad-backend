package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.AcaoDTO;
import me.samcefalo.sistemaiadbackend.dtos.JogoDTO;
import me.samcefalo.sistemaiadbackend.models.Acao;
import me.samcefalo.sistemaiadbackend.models.Jogo;
import org.springframework.stereotype.Component;

@Component
public class AcaoMapper extends MapperImpl<Acao, AcaoDTO> {

    public AcaoMapper() {
        super.configure(Jogo.class, JogoDTO.class);
        super.configure(Acao.class, AcaoDTO.class);
    }

}
