package me.samcefalo.sistemaiadbackend.dtos;

import lombok.*;
import me.samcefalo.sistemaiadbackend.models.enums.UserRole;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@User
public class UserDTO implements Serializable {

    private int id;
    @NotBlank(message = "O nome é obrigatório.")
    @Length(min = 3, max = 100, message = "Insira um nome com {min}-{max} caracteres.")
    private String nome;
    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "Email inválido.")
    private String email;
    private Set<Integer> userRoles = new HashSet<>();

    public Set<UserRole> getUserRoles() {
        return this.userRoles.stream().map(role -> UserRole.toEnum(role)).collect(Collectors.toSet());
    }

    public void addRole(UserRole userRole) {
        this.userRoles.add(userRole.getId());
    }

}
