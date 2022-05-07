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
import java.util.stream.Stream;

@Service
public class EstatisticaService {

    @Autowired
    private AcaoService acaoService;
    @Autowired
    private AtletaService atletaService;
    @Autowired
    private EquipeService equipeService;
    @Autowired
    private JogoService jogoService;
    @Autowired
    private EstatisticaUtils utils;
    @Autowired
    private ClassUtil classUtil;

    public Estatistica getEstatistica(String categoria) {
        Class<?> categoriaClass = getClass(categoria);
        return getEstatistica(categoriaClass);
    }

    public Estatistica getEstatistica(Class<?> categoria) {
        List<Acao> acoes = acaoService.findAll(categoria);

        return getEstatistica(getAcoesGeral(categoria),
                toIntegerList(acoes, false),
                toIntegerList(acoes, true));
    }

    public Estatistica getEstatisticaFromJogador(int atletaId, String categoria) {
        Class<?> categoriaClass = getClass(categoria);
        return getEstatisticaFromJogador(atletaId, categoriaClass);
    }

    public Estatistica getEstatisticaFromJogador(int atletaId, Class<?> categoria) {
        List<Acao> acoes = atletaService.findAcoes(atletaId, categoria);

        return getEstatistica(getAcoesGeral(categoria),
                toIntegerList(acoes, false),
                toIntegerList(acoes, true), acoes);
    }

    public Estatistica getEstatisticaFromEquipe(int equipeId, String categoria) {
        Class<?> categoriaClass = getClass(categoria);
        return getEstatisticaFromEquipe(equipeId, categoriaClass);
    }

    public Estatistica getEstatisticaFromEquipe(int equipeId, Class<?> categoria) {
        List<Acao> acoes = equipeService.findAcoes(equipeId, categoria);

        return getEstatistica(getAcoesGeral(categoria),
                toIntegerList(acoes, false),
                toIntegerList(acoes, true), acoes);
    }

    public Estatistica getEstatisticaFromJogo(int jogoId, String categoria) {
        Class<?> categoriaClass = getClass(categoria);
        return getEstatisticaFromJogo(jogoId, categoriaClass);
    }

    public Estatistica getEstatisticaFromJogo(int jogoId, Class<?> categoria) {
        List<Acao> acoes = jogoService.findAcoes(jogoId, categoria);

        return getEstatistica(getAcoesGeral(categoria),
                toIntegerList(acoes, false),
                toIntegerList(acoes, true), acoes);
    }

    public Estatistica getEstatistica(List<Integer> acoesGeral, List<Integer> acoes, List<Integer> acoesOnlyExito, List<Acao> acoesList) {
        Estatistica estatistica = getEstatistica(acoesGeral, acoes, acoesOnlyExito);
        return addIndice(estatistica, acoesList);
    }

    public Estatistica getEstatistica(List<Integer> acoesGeral, List<Integer> acoes, List<Integer> acoesOnlyExito) {
        return Estatistica.builder()
                .media(utils.getAvg(acoes))
                .variancia(utils.getVariance(acoes))
                .desvio_padrao(utils.getStandardDeviation(acoes))
                .total(acoes.size()).totalExito(acoesOnlyExito.size())
                .min(utils.getMin(acoes)).max(utils.getMax(acoes))
                .scoreZ(utils.getScoreZ(utils.getAvg(acoes), acoesGeral))
                .scoreT(utils.getScoreT(utils.getAvg(acoes), acoesGeral))
                .moda(utils.getModa(acoes))
                .mediana(utils.getMediana(acoes))
                .sum(utils.getSum(acoes))
                .build();
    }

    public Estatistica addIndice(Estatistica estatistica, List<Acao> acoes) {
        estatistica.setIndice(estatistica.getSum() / getMaxPontuacao(acoes));
        return estatistica;
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

    private List<Integer> getAcoesGeral(Class<?> categoria) {
        return getAcoes(categoria, false);
    }

    private List<Integer> getAcoes(Class<?> categoria, boolean onlyExito) {
        List<Acao> acoes;
        if (categoria.equals(Acao.class)) {
            acoes = acaoService.findAll();
        } else
            acoes = acaoService.findAllByCategoria(categoria);

        return toIntegerList(acoes, onlyExito);
    }

    private double getMaxPontuacao(List<Acao> acoes) {
        return acoes.stream()
                .mapToInt(Acao::getGrauDificuldade)
                .sum();
    }

    private List<Integer> toIntegerList(List<Acao> acoes, boolean onlyExito) {
        Stream<Acao> acaoStream = onlyExito ? acoes.stream().filter(acao -> acao.isExito()) : acoes.stream();
        return acaoStream
                .mapToInt(Acao::getPontuacao)
                .boxed()
                .collect(Collectors.toList());
    }

}
