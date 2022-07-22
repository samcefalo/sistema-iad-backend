package me.samcefalo.sistemaiadbackend.specifications.criterias;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AcaoCriteria {
    private String placar, categoria;
    private Integer grauDificuldade, user, area, tempo, etapa, jogo, equipe, atleta, esporte, tipoJogo;
    private Boolean exito, gol, posseDeBola;
    private Long tempoInsercao;
    private LocalDate data, minData, maxData;
}
