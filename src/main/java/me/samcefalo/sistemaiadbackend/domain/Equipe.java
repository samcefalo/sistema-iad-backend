package me.samcefalo.sistemaiadbackend.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Equipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;
    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private List<Jogador> jogadores = new ArrayList<>();


}
