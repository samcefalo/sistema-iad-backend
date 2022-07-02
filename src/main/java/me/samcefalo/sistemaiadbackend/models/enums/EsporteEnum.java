package me.samcefalo.sistemaiadbackend.models.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EsporteEnum {

    FUTSAL(1, "Futsal"),
    FUTEBOL(2, "Futebol");
    //HANDEBOL(3, "Handebol"),
    //VOLEI(4, "Volei");

    private int id;
    private String nome;

    EsporteEnum(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static EsporteEnum toEnum(int id) {
        return Arrays.stream(EsporteEnum.values())
                .filter(jogo -> jogo.getId() == id)
                .findAny().orElseThrow(() -> new IllegalArgumentException("Id não encontrado (" + id + ")."));
    }

}
