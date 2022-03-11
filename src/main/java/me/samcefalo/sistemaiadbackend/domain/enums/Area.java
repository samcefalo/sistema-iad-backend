package me.samcefalo.sistemaiadbackend.domain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Area {

    DEFENSIVO(1, "Defensivo"),
    INTERMEDIARIO(2, "Intermediario"),
    OFENSIVO(3, "Ofensivo");

    private int id;
    private String area;

    Area(int id, String area) {
        this.id = id;
        this.area = area;
    }

    public static Area toEnum(int id) {
        return Arrays.stream(Area.values())
                .filter(area -> area.getId() == id)
                .findAny().orElseThrow(() -> new IllegalArgumentException("Id não encontrado (" + id + ")."));
    }

}
