package me.samcefalo.sistemaiadbackend.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@tipo")
public abstract class Acao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int grauDificuldade;
    private int area;
    private boolean exito;
    @Transient
    private int pontuacao;
    @Transient
    private final int maxPontuacao = 4;
    private String placar;
    private int tempo;
    private int etapa;
    private long tempoInsercao;
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "jogo_id")
    @JsonIgnore
    private Jogo jogo;

    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    @ManyToOne
    @JoinColumn(name = "atleta_id")
    private Atleta atleta;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public int getPontuacao() {
        int pontuacao = 0;
        if (isExito()) {
            pontuacao += getGrauDificuldade();
        } else {
            pontuacao -= (getMaxPontuacao() - getGrauDificuldade()) + 1;
        }
        return pontuacao;
    }
}
