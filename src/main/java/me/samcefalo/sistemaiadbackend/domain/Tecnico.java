package me.samcefalo.sistemaiadbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Tecnico extends Entidade {

    @JsonIgnore
    @ElementCollection
    @CollectionTable(name = "TECNICO_JOGO")
    @ToString.Exclude
    private Set<Jogo> jogos = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

}
