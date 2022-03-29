package me.samcefalo.sistemaiadbackend.domain.dto;

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
public abstract class JogoDTO implements Serializable {

    @Min(value = 1, message = "A situação do jogo é obrigatória.")
    private int situacaoJogo;

}
