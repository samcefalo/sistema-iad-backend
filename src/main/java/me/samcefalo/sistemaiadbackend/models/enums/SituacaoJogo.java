package me.samcefalo.sistemaiadbackend.models.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SituacaoJogo {

    INICIADO(1, "Iniciado"),
    ENCERRADO(2, "Encerrado");

    private int id;
    private String situacao;

    SituacaoJogo(int id, String situacao) {
        this.id = id;
        this.situacao = situacao;
    }

    public static SituacaoJogo toEnum(int id) {
        return Arrays.stream(SituacaoJogo.values())
                .filter(situacao -> situacao.getId() == id)
                .findAny().orElseThrow(() -> new IllegalArgumentException("Id não encontrado (" + id + ")."));
    }

}
