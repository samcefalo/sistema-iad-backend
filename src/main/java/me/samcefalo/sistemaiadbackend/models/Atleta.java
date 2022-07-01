package me.samcefalo.sistemaiadbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Atleta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Transient
    private int idade;
    private String nome;
    private LocalDate data_nascimento;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private int numero;
    private int sexo;

    @JsonIgnore
    @ManyToMany(mappedBy = "atletas", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Jogo> jogos = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "atleta", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Acao> acoes = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    public int getIdade() {
        if (this.data_nascimento == null) return 0;

        return Period.between(this.data_nascimento, LocalDate.now()).getYears();
    }
}
