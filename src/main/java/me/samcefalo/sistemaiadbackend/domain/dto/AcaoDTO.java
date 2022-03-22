package me.samcefalo.sistemaiadbackend.domain.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class AcaoDTO implements Serializable {

    private int id, grauDificuldade, area, equipeId, jogadorId;
    private boolean exito;
    private String tipo;

}
