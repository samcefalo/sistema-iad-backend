package me.samcefalo.sistemaiadbackend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Desarme extends Acao {

    private boolean posseDeBola;
    @Transient
    private final int bonificacaoPosse = 1;

    @Override
    public int getMaxPontuacao() {
        return 3;
    }

    @Override
    public int getPontuacao() {
        int pontuacao = super.getPontuacao();
        if (isPosseDeBola() && isExito()) {
            pontuacao += getBonificacaoPosse();
        }
        return pontuacao;
    }
}
