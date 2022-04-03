package me.samcefalo.sistemaiadbackend.dtos;

import lombok.*;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.EquipeValid;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JogadorDTO extends EntidadeDTO {

    @Min(value = 1, message = "O número é obrigatório.")
    private int numero;
    @EquipeValid
    private EquipeDTO equipe;
    private boolean expulso, titular;

}
