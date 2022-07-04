package me.samcefalo.sistemaiadbackend.specifications.criterias;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EquipeCriteria {
    private String nome;
    private Integer atletaId, userId, jogoId;
}
