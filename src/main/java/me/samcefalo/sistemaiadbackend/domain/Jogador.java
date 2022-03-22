package me.samcefalo.sistemaiadbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Jogador extends Entidade {

    private int numero;
    private boolean expulso, titular;

    @JsonIgnore
    @ManyToMany(mappedBy = "jogadores", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Jogo> jogos = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

}
