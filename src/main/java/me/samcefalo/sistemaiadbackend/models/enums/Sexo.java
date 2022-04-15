package me.samcefalo.sistemaiadbackend.models.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Sexo {

    MASCULINO(1, "Masculino"),
    FEMININO(2, "Feminino"),
    NAO_INFORMADO(3, "Não Informado");

    private int id;
    private String descricao;

    Sexo(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static Sexo toEnum(int id) {
        return Arrays.stream(Sexo.values())
                .filter(area -> area.getId() == id)
                .findAny().orElseThrow(() -> new IllegalArgumentException("Id não encontrado (" + id + ")."));
    }

}
