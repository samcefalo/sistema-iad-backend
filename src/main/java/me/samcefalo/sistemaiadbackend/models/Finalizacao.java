package me.samcefalo.sistemaiadbackend.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Finalizacao extends Acao {

    private boolean gol;
    @Transient
    private final int bonificacaoGol = 2;

    @Override
    public int getMaxPontuacao() {
        return 4;
    }

    @Override
    public int getPontuacao() {
        int pontuacao = super.getPontuacao();
        if (isGol() && isExito()) {
            pontuacao += getBonificacaoGol();
        }
        return pontuacao;
    }

}
