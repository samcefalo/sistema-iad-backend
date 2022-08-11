package me.samcefalo.sistemaiadbackend.estatistica;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class Estatistica implements Serializable {

    private double precisao, media, mediaTempoInsercao, variancia, desvio_padrao, scoreT, scoreZ, max, min, mediana, sum, indice, percentil;
    private int total, totalExito;
    private List<Integer> moda;

}
