package me.samcefalo.sistemaiadbackend.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class EntidadeDTO implements Serializable {

    private int id;
    private String nome;

}
