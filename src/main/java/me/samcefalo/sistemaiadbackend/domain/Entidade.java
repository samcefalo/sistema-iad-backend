package me.samcefalo.sistemaiadbackend.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Entidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    @ElementCollection
    @CollectionTable(name = "ENTIDADE_JOGO")
    private List<Jogo> jogos = new ArrayList<>();


}
