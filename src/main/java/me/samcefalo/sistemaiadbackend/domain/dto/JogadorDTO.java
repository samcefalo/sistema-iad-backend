package me.samcefalo.sistemaiadbackend.domain.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonTypeName("Jogador")
public class JogadorDTO extends EntidadeDTO {

    private int numero, equipeId;
    private boolean expulso, titular;

}
