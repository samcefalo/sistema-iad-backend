package me.samcefalo.sistemaiadbackend.domain.dto;

import lombok.Data;
import me.samcefalo.sistemaiadbackend.domain.Jogo;
import me.samcefalo.sistemaiadbackend.domain.enums.SituacaoJogo;

import java.io.Serializable;

@Data
public class JogoDTO implements Serializable {

    private int id, situacaoJogo, equipe1Id, equipe2Id;

    public JogoDTO(Jogo jogo) {
        this.id = jogo.getId();
        this.situacaoJogo = jogo.getSituacaoJogo().getId();
        this.equipe1Id = jogo.getEquipe1() == null ? 0 : jogo.getEquipe1().getId();
        this.equipe2Id = jogo.getEquipe2() == null ? 0 : jogo.getEquipe2().getId();
    }

    public SituacaoJogo getSituacaoJogo() {
        return SituacaoJogo.toEnum(situacaoJogo);
    }

}
