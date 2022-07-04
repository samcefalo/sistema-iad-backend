package me.samcefalo.sistemaiadbackend.models.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EtapaEnum {

    INICIO(1, "Inicio"),
    MEIO(2, "Meio"),
    FIM(3, "Fim");

    private int id;
    private String etapa;

    EtapaEnum(int id, String etapa) {
        this.id = id;
        this.etapa = etapa;
    }

    public static EtapaEnum toEnum(int id) {
        return Arrays.stream(EtapaEnum.values())
                .filter(etapa -> etapa.getId() == id)
                .findAny().orElseThrow(() -> new IllegalArgumentException("Id não encontrado (" + id + ")."));
    }

}
