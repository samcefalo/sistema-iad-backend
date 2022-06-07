package me.samcefalo.sistemaiadbackend.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Jogo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int situacaoJogo;
    private int tipoJogo;
    private LocalDate data;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "EQUIPE_JOGO",
            joinColumns = @JoinColumn(name = "jogo_id"),
            inverseJoinColumns = @JoinColumn(name = "equipe_id"))
    private Set<Equipe> equipes = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ATLETA_JOGO",
            joinColumns = @JoinColumn(name = "jogo_id"),
            inverseJoinColumns = @JoinColumn(name = "atleta_id"))
    private Set<Atleta> atletas = new HashSet<>();

    @OneToMany(mappedBy = "jogo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Acao> acoes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
