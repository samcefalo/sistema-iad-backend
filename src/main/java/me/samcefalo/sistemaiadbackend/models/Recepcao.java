package me.samcefalo.sistemaiadbackend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Recepcao extends Acao {

    @Override
    public int getMaxPontuacao() {
        return 4;
    }

}
