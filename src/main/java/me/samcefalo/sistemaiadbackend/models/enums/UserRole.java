package me.samcefalo.sistemaiadbackend.models.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum UserRole {

    DEFAULT(1, "ROLE_DEFAULT"),
    ADMIN(2, "ROLE_ADMIN");

    private int id;
    private String descricao;

    UserRole(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static UserRole toEnum(int id) {
        return Arrays.stream(UserRole.values())
                .filter(area -> area.getId() == id)
                .findAny().orElseThrow(() -> new IllegalArgumentException("Id não encontrado (" + id + ")."));
    }

}
