package me.samcefalo.sistemaiadbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Jogador extends Entidade {

    private int numero;
    private boolean expulso, titular;


    @JsonIgnore
    @ManyToMany(mappedBy = "jogadores")
    private List<Jogo> jogos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;


}
