package me.samcefalo.sistemaiadbackend.specifications.criterias;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AcaoCriteria {
    private String placar, categoria;
    private Integer grauDificuldade, userId, area, tempo, etapa, jogoId, equipeId, atletaId, esporte, tipoJogo;
    private Boolean exito, gol, posseDeBola;
    private long tempoInsercao;
    private LocalDate data;
}
