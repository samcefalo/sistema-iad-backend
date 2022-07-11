package me.samcefalo.sistemaiadbackend.estatistica;

import lombok.Builder;
import lombok.Data;
import me.samcefalo.sistemaiadbackend.models.Acao;

import java.util.List;

@Data
@Builder
public class EstatisticaQuerry {

    private List<Acao> acoes;
    private List<Acao> acoesGlobal;

}
