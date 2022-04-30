package me.samcefalo.sistemaiadbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Atleta extends Entidade {

    private int numero;
    private boolean expulso, titular;
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
}
