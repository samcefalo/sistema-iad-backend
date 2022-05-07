package me.samcefalo.sistemaiadbackend.estatistica;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class PrincipiosTaticas implements Serializable {

    private double penetracao;

}
