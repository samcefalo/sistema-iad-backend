package me.samcefalo.sistemaiadbackend.domain.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Equipe;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Jogador;

import javax.validation.constraints.Min;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@tipo")
public abstract class AcaoDTO implements Serializable {

    private int id;
    @Min(value = 1, message = "O grau de dificuldade é obrigatório.")
    private int grauDificuldade;
    @Min(value = 1, message = "A area é obrigatória.")
    private int area;
    @Equipe
    @Min(value = 1, message = "O id da equipe é obrigatório.")
    private int equipeId;
    @Jogador
    @Min(value = 1, message = "O id do jogador é obrigatório.")
    private int jogadorId;
    private boolean exito;

}
