package me.samcefalo.sistemaiadbackend.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EquipeDTO implements Serializable {

    private int id;
    private String nome;

}
