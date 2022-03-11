package me.samcefalo.sistemaiadbackend.domain;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Jogador extends Entidade {

    private int numero;
    private boolean expulso, reserva;

}
