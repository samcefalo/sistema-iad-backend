package me.samcefalo.sistemaiadbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.samcefalo.sistemaiadbackend.models.enums.UserRole;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String email;
    @JsonIgnore
    private String senha;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles")
    private Set<Integer> userRoles = new HashSet<>();

    public Set<UserRole> getUserRolesEnum() {
        return this.userRoles.stream().map(role -> UserRole.toEnum(role)).collect(Collectors.toSet());
    }

    public void addRole(UserRole userRole) {
        this.userRoles.add(userRole.getId());
    }

}
