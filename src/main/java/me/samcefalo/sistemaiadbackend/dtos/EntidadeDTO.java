package me.samcefalo.sistemaiadbackend.dtos;

import lombok.*;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Age;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class EntidadeDTO implements Serializable {

    private int id;
    @NotBlank(message = "O nome é obrigatório.")
    @Length(min = 3, max = 100, message = "Insira um nome com {min}-{max} caracteres.")
    private String nome;
    private UserDTO user;
    @Age
    private LocalDate data_nascimento;

    public int getIdade() {
        if (this.data_nascimento == null) return 0;

        return Period.between(this.data_nascimento, LocalDate.now()).getYears();
    }

}
