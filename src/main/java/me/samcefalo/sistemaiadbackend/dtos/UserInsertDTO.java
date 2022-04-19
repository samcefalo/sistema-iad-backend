package me.samcefalo.sistemaiadbackend.dtos;

import lombok.*;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.UserInsert;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.UserRoleId;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@UserInsert
public class UserInsertDTO implements Serializable {

    private int id;
    @NotBlank(message = "O username é obrigatório.")
    @Length(min = 3, max = 100, message = "Insira um nome com {min}-{max} caracteres.")
    private String username;
    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "Email inválido.")
    private String email;
    @NotBlank(message = "A senha é obrigatória.")
    private String senha;
    @UserRoleId
    private Set<Integer> userRoles = new HashSet<>();

}
