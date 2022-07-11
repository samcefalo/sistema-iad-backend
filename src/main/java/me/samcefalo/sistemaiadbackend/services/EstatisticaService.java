package me.samcefalo.sistemaiadbackend.services;

import me.samcefalo.sistemaiadbackend.estatistica.Estatistica;
import me.samcefalo.sistemaiadbackend.estatistica.EstatisticaQuerry;
import me.samcefalo.sistemaiadbackend.estatistica.utils.EstatisticaUtils;
import me.samcefalo.sistemaiadbackend.services.utils.AcaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstatisticaService {

    @Autowired
    private EstatisticaUtils utils;
    @Autowired
    private AcaoUtils acaoUtils;

    public Estatistica getEstatistica(EstatisticaQuerry querry) {
        List<Integer> acoes = acaoUtils.parseAcaoToInteger(querry.getAcoes());
        List<Integer> acoesGlobal = acaoUtils.parseAcaoToInteger(querry.getAcoesGlobal());
        List<Integer> acoesOnlyExito = acaoUtils.parseAcaoToInteger(acaoUtils.getOnlyExito(querry.getAcoes()));

        Estatistica estatistica = Estatistica.builder()
                .media(utils.getAvg(acoes))
                .variancia(utils.getVariance(acoes))
                .desvio_padrao(utils.getStandardDeviation(acoes))
                .total(acoes.size()).totalExito(acoesOnlyExito.size())
                .min(utils.getMin(acoes)).max(utils.getMax(acoes))
                .scoreZ(utils.getScoreZ(utils.getAvg(acoes), acoesGlobal))
                .scoreT(utils.getScoreT(utils.getAvg(acoes), acoesGlobal))
                .moda(utils.getModa(acoes))
                .mediana(utils.getMediana(acoes))
                .sum(utils.getSum(acoes))
                .build();
        estatistica.setIndice(estatistica.getSum() / utils.getMaxPontuacao(querry.getAcoes()));
        return estatistica;
    }

}
