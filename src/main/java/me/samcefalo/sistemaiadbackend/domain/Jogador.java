package me.samcefalo.sistemaiadbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Jogador extends Entidade {

    private int numero;
    private boolean expulso, titular;

    @ManyToMany(mappedBy = "jogadores")
    @ToString.Exclude
    @JsonIgnore
    private Set<Jogo> jogos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;


}
