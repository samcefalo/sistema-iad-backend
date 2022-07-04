package me.samcefalo.sistemaiadbackend.models.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TipoJogoEnum {

    AMISTOSO(1, "Amistoso"),
    CAMPEONATO_REGULAR(2, "Campeonato Regular"),
    DECISIVO(3, "Decisivo"),
    MATA_MATA(4, "Mata-Mata");

    private int id;
    private String tipo;

    TipoJogoEnum(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public static TipoJogoEnum toEnum(int id) {
        return Arrays.stream(TipoJogoEnum.values())
                .filter(tipo -> tipo.getId() == id)
                .findAny().orElseThrow(() -> new IllegalArgumentException("Id não encontrado (" + id + ")."));
    }

}
