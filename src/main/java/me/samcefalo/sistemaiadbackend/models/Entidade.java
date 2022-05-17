package me.samcefalo.sistemaiadbackend.models;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Entidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Transient
    private int idade;
    private String nome;
    private LocalDate data_nascimento;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public int getIdade() {
        if (this.data_nascimento == null) return 0;

        return Period.between(this.data_nascimento, LocalDate.now()).getYears();
    }

}
