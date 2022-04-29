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
@JsonTypeName("Finalizacao")
public class FinalizacaoDTO extends AcaoDTO {

    private boolean gol;
    private final int maxPontuacao = 4;
    private final int bonificacaoGol = 2;

    @Override
    public int getPontuacao() {
        int pontuacao = 0;
        if (isExito()) {
            pontuacao += getGrauDificuldade();
            if (gol) {
                pontuacao += bonificacaoGol;
            }
        } else {
            pontuacao -= (maxPontuacao - getGrauDificuldade()) + 1;
        }
        return pontuacao;
    }

}
