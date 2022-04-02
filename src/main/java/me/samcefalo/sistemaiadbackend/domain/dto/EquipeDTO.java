package me.samcefalo.sistemaiadbackend.domain.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EquipeDTO implements Serializable {

    @Min(value = 1, message = "O id é obrigatório.")
    private int id;
    @NotBlank(message = "O nome é obrigatório.")
    @Length(min = 3, max = 100, message = "Insira um nome com {min}-{max} caracteres.")
    private String nome;

}
