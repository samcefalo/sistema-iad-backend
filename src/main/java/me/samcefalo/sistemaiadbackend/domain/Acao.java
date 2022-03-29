package me.samcefalo.sistemaiadbackend.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Acao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int grauDificuldade;
    private int area;
    private boolean exito;

    @ManyToOne
    @JoinColumn(name = "jogo_id")
    @JsonIgnore
    private Jogo jogo;

    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    @ManyToOne
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;

}
