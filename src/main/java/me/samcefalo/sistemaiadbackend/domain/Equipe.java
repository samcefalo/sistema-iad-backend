package me.samcefalo.sistemaiadbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Equipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;

    @JsonIgnore
    @OneToOne(mappedBy = "equipe")
    @ToString.Exclude
    private Tecnico tecnico;

    @JsonIgnore
    @OneToMany(mappedBy = "equipe")
    @ToString.Exclude
    private Set<Jogador> jogadores = new HashSet<>();

}
