package me.samcefalo.sistemaiadbackend.specifications.criterias;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AtletaCriteria {
    private String nome;
    private Integer numero, userId, sexo, jogoId, acaoId, equipeId;
}
