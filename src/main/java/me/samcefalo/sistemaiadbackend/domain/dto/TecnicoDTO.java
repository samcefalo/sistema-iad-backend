package me.samcefalo.sistemaiadbackend.domain.dto;

import lombok.*;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Equipe;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TecnicoDTO extends EntidadeDTO {

    @Equipe
    @Min(value = 1, message = "O id da equipe é obrigatório.")
    private int equipeId;

}
