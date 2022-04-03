package me.samcefalo.sistemaiadbackend.dtos;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Equipe;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Jogador;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Jogo;

import javax.validation.constraints.Min;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PasseDTO.class, name = "Passe"),
        @JsonSubTypes.Type(value = DesarmeDTO.class, name = "Desarme"),
        @JsonSubTypes.Type(value = DribleDTO.class, name = "Drible"),
        @JsonSubTypes.Type(value = FinalizacaoDTO.class, name = "Finalizacao")
})
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

    @Jogo
    @Min(value = 1, message = "O id do jogo é obrigatório.")
    private int jogoId;
    private boolean exito;

}
