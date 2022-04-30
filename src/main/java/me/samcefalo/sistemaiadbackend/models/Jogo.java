package me.samcefalo.sistemaiadbackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@tipo")
public abstract class Jogo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int situacaoJogo;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date data;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "EQUIPE_JOGO",
            joinColumns = @JoinColumn(name = "jogo_id"),
            inverseJoinColumns = @JoinColumn(name = "equipe_id"))
    private Set<Equipe> equipes = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ATLETA_JOGO",
            joinColumns = @JoinColumn(name = "jogo_id"),
            inverseJoinColumns = @JoinColumn(name = "atleta_id"))
    private Set<Atleta> atletas = new HashSet<>();

    @OneToMany(mappedBy = "jogo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Acao> acoes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
