package me.samcefalo.sistemaiadbackend.domain.dto;

import lombok.Data;
import me.samcefalo.sistemaiadbackend.domain.Acao;
import me.samcefalo.sistemaiadbackend.domain.enums.Area;

import java.io.Serializable;

@Data
public class AcaoDTO implements Serializable {

    private int id, jogadorId, equipeId, grauDificuldade, area;
    private String nome;
    private boolean exito;

    public AcaoDTO(Acao acao) {
        this.id = acao.getId();
        this.jogadorId = acao.getJogador().getId();
        this.equipeId = acao.getEquipe().getId();
        this.grauDificuldade = acao.getGrauDificuldade();
        this.area = acao.getArea().getId();
        this.nome = acao.getClass().getName();
        this.exito = acao.isExito();
    }

    public Area getArea() {
        return Area.toEnum(area);
    }

}
