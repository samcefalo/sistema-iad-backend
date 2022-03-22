package me.samcefalo.sistemaiadbackend.domain;

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
    @CollectionTable(name = "JOGO_TECNICO")
    @ToString.Exclude
    private Set<Jogo> jogos = new HashSet<>();

    @JsonIgnore
    @OneToOne(mappedBy = "tecnico", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Equipe equipe;

}
