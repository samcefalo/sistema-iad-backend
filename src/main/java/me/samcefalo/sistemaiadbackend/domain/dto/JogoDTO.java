package me.samcefalo.sistemaiadbackend.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public abstract class JogoDTO implements Serializable {

    private int id, situacaoJogo;
    private Set<EquipeDTO> equipes = new HashSet<>();

}
