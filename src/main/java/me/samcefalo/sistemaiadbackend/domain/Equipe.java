package me.samcefalo.sistemaiadbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private Tecnico tecnico;

    @JsonIgnore
    @OneToMany(mappedBy = "equipe", fetch = FetchType.LAZY)
    private List<Jogador> jogadores = new ArrayList<>();

    public Equipe(String nome) {
        this.nome = nome;
    }

}
