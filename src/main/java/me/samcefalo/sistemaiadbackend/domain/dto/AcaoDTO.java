package me.samcefalo.sistemaiadbackend.domain.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@tipo")
public abstract class AcaoDTO implements Serializable {

    private int id, grauDificuldade, area, equipeId, jogadorId;
    private boolean exito;

}
