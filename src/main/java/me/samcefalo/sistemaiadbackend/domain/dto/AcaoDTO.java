package me.samcefalo.sistemaiadbackend.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AcaoDTO implements Serializable {

    private int id, grauDificuldade, area, equipeId, jogadorId, jogoId;
    private boolean exito;

}
