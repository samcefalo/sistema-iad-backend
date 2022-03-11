package me.samcefalo.sistemaiadbackend.domain;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Finalizacao extends Acao {

    private boolean gol;

}
