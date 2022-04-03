package me.samcefalo.sistemaiadbackend.dtos;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.EquipeValid;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.JogadorValid;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.JogoValid;

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

    @EquipeValid
    private EquipeDTO equipe;
    @JogadorValid
    private JogadorDTO jogador;
    @JogoValid
    private JogoDTO jogo;

    private boolean exito;

}
