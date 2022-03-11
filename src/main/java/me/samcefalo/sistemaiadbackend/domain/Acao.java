package me.samcefalo.sistemaiadbackend.domain;


import lombok.Data;
import me.samcefalo.sistemaiadbackend.domain.enums.Area;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Acao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String nome;
    private int grauDificuldade;
    private boolean exito;

    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;

    private Area area;
    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    @ManyToOne
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;

}
