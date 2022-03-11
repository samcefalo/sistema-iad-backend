package me.samcefalo.sistemaiadbackend.domain;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Desarme extends Acao {

    private boolean posseDeBola;

}
