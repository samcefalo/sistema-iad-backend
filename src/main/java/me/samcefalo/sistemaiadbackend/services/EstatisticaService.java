package me.samcefalo.sistemaiadbackend.services;

import me.samcefalo.sistemaiadbackend.estatistica.Estatistica;
import me.samcefalo.sistemaiadbackend.estatistica.utils.EstatisticaUtils;
import me.samcefalo.sistemaiadbackend.models.Acao;
import me.samcefalo.sistemaiadbackend.services.exceptions.ObjectNotFoundException;
import me.samcefalo.sistemaiadbackend.services.utils.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstatisticaService {

    @Autowired
    private AcaoService acaoService;
    @Autowired
    private JogadorService jogadorService;
    @Autowired
    private EstatisticaUtils utils;
    @Autowired
    private ClassUtil classUtil;

    public Estatistica getEstatistica(String categoria) {
        List<Integer> list = getAcoes(getClass(categoria), false);
        List<Integer> listExito = getAcoes(getClass(categoria), true);

        return Estatistica.builder()
                .media(utils.getAvg(list))
                .variancia(utils.getVariance(list))
                .desvio_padrao(utils.getStandardDeviation(list))
                .total(list.size()).totalExito(listExito.size())
                .min(utils.getMin(list)).max(utils.getMax(list))
                .build();
    }

    public Estatistica getEstatistica(int jogadorId, String categoria) {
        List<Integer> listAcoes = getAcoes(getClass(categoria), false);
        List<Integer> list = getAcoes(jogadorId, getClass(categoria), false);
        List<Integer> listExito = getAcoes(jogadorId, getClass(categoria), true);

        return Estatistica.builder()
                .media(utils.getAvg(list))
                .variancia(utils.getVariance(list))
                .desvio_padrao(utils.getStandardDeviation(list))
                .total(list.size()).totalExito(listExito.size())
                .min(utils.getMin(list)).max(utils.getMax(list))
                .scoreZ(utils.getScoreZ(utils.getAvg(list), listAcoes))
                .scoreT(utils.getScoreT(utils.getAvg(list), listAcoes))
                .build();
    }

    private Class<?> getClass(String categoria) {
        Class<?> categoriaClass;
        try {
            categoriaClass = classUtil.getAcaoClass(categoria);
        } catch (ClassNotFoundException e) {
            throw new ObjectNotFoundException("Acao não encontrada. ID: " + categoria + ", Tipo: " + Acao.class.getSimpleName());
        }
        return categoriaClass;
    }

    private List<Integer> getAcoes(Class<?> categoria, boolean onlyExito) {
        List<Acao> acoes;
        if (categoria.equals(Acao.class)) {
            acoes = acaoService.findAll();
        } else
            acoes = acaoService.findAllByCategoria(categoria);

        return toIntegerList(acoes, onlyExito);
    }

    private List<Integer> getAcoes(int jogadorId, Class<?> categoria, boolean onlyExito) {
        List<Acao> acoes;
        if (categoria.equals(Acao.class)) {
            acoes = jogadorService.findAcoes(jogadorId);
        } else
            acoes = jogadorService.findAcoesByCategoria(jogadorId, categoria);

        return toIntegerList(acoes, onlyExito);
    }

    private List<Integer> toIntegerList(List<Acao> acoes, boolean onlyExito) {
        if (onlyExito) {
            return acoes.stream()
                    .filter(acao -> acao.isExito())
                    .mapToInt(Acao::getPontuacao)
                    .boxed()
                    .collect(Collectors.toList());
        }
        return acoes.stream()
                .mapToInt(Acao::getPontuacao)
                .boxed()
                .collect(Collectors.toList());
    }

}
