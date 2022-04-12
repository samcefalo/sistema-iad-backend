package me.samcefalo.sistemaiadbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Equipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;

    @JsonIgnore
    @OneToOne(mappedBy = "equipe", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Tecnico tecnico;

    @JsonIgnore
    @OneToMany(mappedBy = "equipe", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Jogador> jogadores = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "equipes", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Jogo> jogos = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "equipe", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Acao> acoes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

