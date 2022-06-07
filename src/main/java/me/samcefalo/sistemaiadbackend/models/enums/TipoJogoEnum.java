package me.samcefalo.sistemaiadbackend.models.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TipoJogoEnum {

    FUTSAL(1, 10, "Futsal"),
    FUTEBOL(2, 22, "Futebol");
    //HANDEBOL(3, 14, "Handebol"),
    //VOLEI(4, 12, "Volei");

    private int slots, id;
    private String nome;

    TipoJogoEnum(int id, int slots, String nome) {
        this.id = id;
        this.slots = slots;
        this.nome = nome;
    }

    public static TipoJogoEnum toEnum(int id) {
        return Arrays.stream(TipoJogoEnum.values())
                .filter(jogo -> jogo.getId() == id)
                .findAny().orElseThrow(() -> new IllegalArgumentException("Id não encontrado (" + id + ")."));
    }

}
