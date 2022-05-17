package me.samcefalo.sistemaiadbackend.dtos;

import lombok.*;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Team;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AtletaDTO extends EntidadeDTO {

    @Min(value = 1, message = "O número é obrigatório.")
    private int numero;
    @Team
    private EquipeDTO equipe;
    @Min(value = 1, message = "O sexo é obrigatório.")
    private int sexo;

}
