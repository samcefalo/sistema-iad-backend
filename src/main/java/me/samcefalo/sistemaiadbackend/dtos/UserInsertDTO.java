package me.samcefalo.sistemaiadbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.samcefalo.sistemaiadbackend.models.enums.UserRole;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@ToString
@User
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
    private Set<Integer> userRoles = new HashSet<>();

    public UserInsertDTO() {
        this.addRole(UserRole.DEFAULT);
    }

    public void addRole(UserRole userRole) {
        this.userRoles.add(userRole.getId());
    }

}
