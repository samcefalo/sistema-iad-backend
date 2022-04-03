package me.samcefalo.sistemaiadbackend.dtos;

import lombok.*;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JogadorDTO extends EntidadeDTO {

    @Min(value = 1, message = "O número é obrigatório.")
    private int numero;
    /*@Equipe
    @Min(value = 1, message = "O id da equipe é obrigatório.")
    private int equipeId;
     */

    private boolean expulso, titular;

}
