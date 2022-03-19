package me.samcefalo.sistemaiadbackend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Jogo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int situacaoJogo;

    @ManyToMany
    @JoinTable(name = "JOGO_EQUIPE",
            joinColumns = @JoinColumn(name = "equipe_id"),
            inverseJoinColumns = @JoinColumn(name = "jogos_id"))
    private Set<Equipe> equipes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "JOGO_JOGADOR",
            joinColumns = @JoinColumn(name = "jogador_id"),
            inverseJoinColumns = @JoinColumn(name = "jogos_id"))
    private Set<Jogador> jogadores = new HashSet<>();

    @OneToMany(mappedBy = "jogo")
    private Set<Acao> acoes = new HashSet<>();

}
