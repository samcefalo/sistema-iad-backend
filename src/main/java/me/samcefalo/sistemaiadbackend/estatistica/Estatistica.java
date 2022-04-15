package me.samcefalo.sistemaiadbackend.estatistica;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Estatistica implements Serializable {

    private double media, variancia, desvio_padrao, scoreT, scoreZ, max, min;
    private int total, totalExito;

}
