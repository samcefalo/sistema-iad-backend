package me.samcefalo.sistemaiadbackend.dtos;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserUpdateDTO implements Serializable {

    private int id;
    @NotBlank(message = "O username é obrigatório.")
    @Length(min = 3, max = 100, message = "Insira um nome com {min}-{max} caracteres.")
    private String nome;
    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

}
