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
public class Tecnico extends Entidade {

    @JsonIgnore
    @ElementCollection
    @CollectionTable(name = "TECNICO_JOGO")
    @ToString.Exclude
    private Set<Jogo> jogos = new HashSet<>();

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipe_id")
    @ToString.Exclude
    private Equipe equipe;

}
