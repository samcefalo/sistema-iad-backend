package me.samcefalo.sistemaiadbackend.models.enums;

import lombok.Getter;

import java.util.Arrays;

@Deprecated
@Getter
public enum TipoAcaoEnum {

    //------------------FUTSAL------------------
    DESARME(1, 3, 1, "Desarme"),
    DRIBLE(2, 2, 0, "Drible"),
    FINALIZACAO(3, 4, 2, "Finalização"),
    PASSE(4, 4, 0, "Passe"),
    RECEPCAO(5, 4, 0, "Recepção");

    private int max_pontuacao, id, bonificacao;
    private String nome;

    TipoAcaoEnum(int id, int max_pontuacao, int bonificacao, String nome) {
        this.id = id;
        this.max_pontuacao = max_pontuacao;
        this.bonificacao = bonificacao;
        this.nome = nome;
    }

    public static TipoAcaoEnum toEnum(int id) {
        return Arrays.stream(TipoAcaoEnum.values())
                .filter(acao -> acao.getId() == id)
                .findAny().orElseThrow(() -> new IllegalArgumentException("Id não encontrado (" + id + ")."));
    }

}
