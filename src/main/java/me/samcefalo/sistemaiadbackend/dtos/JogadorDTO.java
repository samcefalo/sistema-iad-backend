package me.samcefalo.sistemaiadbackend.dtos;

import lombok.*;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Team;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JogadorDTO extends EntidadeDTO {

    @Min(value = 1, message = "O número é obrigatório.")
    private int numero;
    @Team
    private EquipeDTO equipe;
    private boolean expulso, titular;
    @Min(value = 1, message = "O sexo é obrigatório.")
    private int sexo;

}
