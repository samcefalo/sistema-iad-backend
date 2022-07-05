package me.samcefalo.sistemaiadbackend.specifications.criterias;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AcaoCriteria {
    private String placar, categoria;
    private Integer grauDificuldade, userId, area, tempo, etapa, jogoId, equipeId, atletaId;
    private Boolean exito, gol, posseDeBola;
}
