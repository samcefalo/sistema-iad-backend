package me.samcefalo.sistemaiadbackend.domain.dto;

import lombok.Data;
import me.samcefalo.sistemaiadbackend.domain.Equipe;

import java.io.Serializable;

@Data
public class EquipeDTO implements Serializable {

    private int id, tecnicoId;
    private String nome;

    public EquipeDTO(Equipe equipe) {
        this.id = equipe.getId();
        this.tecnicoId = equipe.getTecnico().getId();
        this.nome = equipe.getNome();
    }

}
