package me.samcefalo.sistemaiadbackend.domain.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class EntidadeDTO implements Serializable {

    private int id;
    private String nome;

}
