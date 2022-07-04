package me.samcefalo.sistemaiadbackend.specifications.criterias;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JogoCriteria {
    private Integer atletaId, equipeId, userId, esporte, tipo;
}
