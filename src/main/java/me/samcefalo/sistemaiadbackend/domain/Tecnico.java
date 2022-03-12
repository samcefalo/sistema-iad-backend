package me.samcefalo.sistemaiadbackend.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Tecnico extends Entidade {

    @ElementCollection
    @CollectionTable(name = "TECNICO_JOGO")
    private List<Jogo> jogos = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

}
