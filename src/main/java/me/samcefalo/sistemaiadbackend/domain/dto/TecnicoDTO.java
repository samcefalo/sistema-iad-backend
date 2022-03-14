package me.samcefalo.sistemaiadbackend.domain.dto;

import lombok.Data;
import me.samcefalo.sistemaiadbackend.domain.Tecnico;

import java.io.Serializable;

@Data
public class TecnicoDTO implements Serializable {

    private int id, equipeId;
    private String nome;

    public TecnicoDTO(Tecnico tecnico) {
        this.id = tecnico.getId();
        this.equipeId = tecnico.getEquipe().getId();
        this.nome = tecnico.getNome();
    }

}
