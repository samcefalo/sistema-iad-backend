package me.samcefalo.sistemaiadbackend.domain.dto;

import lombok.Data;

@Data
public class JogadorDTO extends EntidadeDTO {

    private int numero, equipeId;
    private boolean expulso, titular;

}
