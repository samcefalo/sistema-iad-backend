package me.samcefalo.sistemaiadbackend.dtos;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonTypeName("Recepção")
public class RecepcaoDTO extends AcaoDTO {

    private int maxPontuacao = 4;

    @Override
    public int getPontuacao() {
        int pontuacao = 0;
        if (isExito()) {
            pontuacao += getGrauDificuldade();
        } else {
            pontuacao -= (maxPontuacao - getGrauDificuldade()) + 1;
        }
        return pontuacao;
    }

}
