package me.samcefalo.sistemaiadbackend.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Jogo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int situacaoJogo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "JOGO_EQUIPE",
            joinColumns = @JoinColumn(name = "equipe_id"),
            inverseJoinColumns = @JoinColumn(name = "jogos_id"))
    private Set<Equipe> equipes = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "JOGO_JOGADOR",
            joinColumns = @JoinColumn(name = "jogador_id"),
            inverseJoinColumns = @JoinColumn(name = "jogos_id"))
    private Set<Jogador> jogadores = new HashSet<>();

    @OneToMany(mappedBy = "jogo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Acao> acoes = new HashSet<>();

}
