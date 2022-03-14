package me.samcefalo.sistemaiadbackend.domain.dto;

import lombok.Data;
import me.samcefalo.sistemaiadbackend.domain.Jogador;

import java.io.Serializable;

@Data
public class JogadorDTO implements Serializable {

    private int id, equipeId, numero;
    private String nome;
    private boolean expulso, titular;

    public JogadorDTO(Jogador jogador) {
        this.id = jogador.getId();
        this.equipeId = jogador.getEquipe().getId();
        this.numero = jogador.getNumero();
        this.nome = jogador.getNome();
        this.expulso = jogador.isExpulso();
        this.titular = jogador.isTitular();
    }

}
