package me.samcefalo.sistemaiadbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Athlete;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Game;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Team;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "classe")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PasseDTO.class, name = "Passe"),
        @JsonSubTypes.Type(value = DesarmeDTO.class, name = "Desarme"),
        @JsonSubTypes.Type(value = DribleDTO.class, name = "Drible"),
        @JsonSubTypes.Type(value = FinalizacaoDTO.class, name = "Finalizacao"),
        @JsonSubTypes.Type(value = RecepcaoDTO.class, name = "Recepcao")
})
public abstract class AcaoDTO implements Serializable {

    private int id;
    @Min(value = 1, message = "O grau de dificuldade é obrigatório.")
    private int grauDificuldade;
    @Min(value = 1, message = "A area é obrigatória.")
    private int area;
    private boolean exito;
    private int pontuacao;
    private String placar;
    private int tempo;
    private int etapa;
    private long tempoInsercao;
    private LocalDate data;

    @Team
    private EquipeDTO equipe;
    @Athlete
    private AtletaDTO atleta;
    @Game
    private JogoDTO jogo;

    @JsonIgnore
    private UserDTO user;

}
