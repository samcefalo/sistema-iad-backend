package me.samcefalo.sistemaiadbackend.dtos;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonTypeName("Desarme")
public class DesarmeDTO extends AcaoDTO {

    private boolean posseDeBola;
    private int maxPontuacao = 3;
    private int bonificacaoPosse = 1;

    @Override
    public int getPontuacao() {
        int pontuacao = 0;
        if (isExito()) {
            pontuacao += getGrauDificuldade();
            if (posseDeBola) {
                pontuacao += bonificacaoPosse;
            }
        } else {
            pontuacao -= (maxPontuacao - getGrauDificuldade()) + 1;
        }
        return pontuacao;
    }
}
