package me.samcefalo.sistemaiadbackend.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class JogoDTO implements Serializable {

    private int id, situacaoJogo, equipe1Id, equipe2Id;

}
