package me.samcefalo.sistemaiadbackend.dtos;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.validation.constraints.Min;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = JogoFutsalDTO.class, name = "JogoFutsal"),
        @JsonSubTypes.Type(value = JogoFutebolDTO.class, name = "JogoFutebol"),
})
public abstract class JogoDTO implements Serializable {

    private int id;
    @Min(value = 1, message = "A situação do jogo é obrigatória.")
    private int situacaoJogo;

}