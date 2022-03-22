package me.samcefalo.sistemaiadbackend.domain;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Finalizacao extends Acao {

    private boolean gol;

}
