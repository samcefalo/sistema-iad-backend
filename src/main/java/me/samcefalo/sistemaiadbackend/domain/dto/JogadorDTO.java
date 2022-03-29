package me.samcefalo.sistemaiadbackend.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JogadorDTO extends EntidadeDTO {

    private int numero, equipeId;
    private boolean expulso, titular;

}
