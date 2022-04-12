package me.samcefalo.sistemaiadbackend.dtos;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Game;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Player;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Team;

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
        @JsonSubTypes.Type(value = FinalizacaoDTO.class, name = "Finalizacao"),
        @JsonSubTypes.Type(value = RecepcaoDTO.class, name = "Recepção")
})
public abstract class AcaoDTO implements Serializable {

    private int id;
    @Min(value = 1, message = "O grau de dificuldade é obrigatório.")
    private int grauDificuldade;
    @Min(value = 1, message = "A area é obrigatória.")
    private int area;
    private boolean exito;

    @Team
    private EquipeDTO equipe;
    @Player
    private JogadorDTO jogador;
    @Game
    private JogoDTO jogo;

    private UserDTO user;


}
