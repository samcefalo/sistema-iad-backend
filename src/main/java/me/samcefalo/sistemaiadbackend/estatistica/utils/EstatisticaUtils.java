package me.samcefalo.sistemaiadbackend.estatistica.utils;

import me.samcefalo.sistemaiadbackend.models.Acao;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EstatisticaUtils {

    public double getAvg(List<Integer> list) {
        return list.stream()
                .mapToInt(Integer::intValue)
                .average().orElse(0);
    }

    public double getMaxPontuacao(List<Acao> acoes) {
        return acoes.stream()
                .mapToInt(Acao::getGrauDificuldade)
                .sum();
    }

    public double getStandardDeviation(List<Integer> list) {
        return Math.sqrt(getVariance(list));
    }

    public double getVariance(List<Integer> list) {
        return list.stream()
                .mapToDouble(Integer::intValue)
                .map(a -> (a - getAvg(list)) * (a - getAvg(list)))
                .reduce(0d, Double::sum) / (getSize(list) - 1);
    }

    public double getMediana(List<Integer> list) {
        if (list.isEmpty()) return 0;
        return list.size() % 2 == 0 ?
                list.stream()
                        .sorted().skip((list.size() / 2) - 1) //ordena e pega a metade
                        .limit(2) //limita os 2 primeiros numeros
                        .mapToInt(Integer::intValue)
                        .average().orElse(0) :
                list.stream()
                        .sorted().skip(list.size() / 2) //ordena e pega a metade
                        .findFirst().orElse(0);
    }

    public List<Integer> getModa(List<Integer> list) {
        if (list.isEmpty()) return new ArrayList<>();
        return list.stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()))
                .entrySet()
                .stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue, TreeMap::new,
                        Collectors.mapping(Map.Entry::getKey,
                                Collectors.toList())))
                .lastEntry()
                .getValue();
    }

    public double getScoreT(double value, List<Integer> list) {
        return 50 + (10 * (getScoreZ(value, list)));
    }

    public double getScoreZ(double value, List<Integer> list) {
        return (value - getAvg(list) / getStandardDeviation(list));
    }

    public double getPercentile(double value, List<Integer> list) {
        Collections.sort(list);
        double bellowValues = list.stream().filter(v -> v < value).count();
        double total = list.size();
        return (bellowValues / total) * 100;
    }

    public double getPrecisao(double value, double total) {
        return (value / total) * 100;
    }

    public double getMax(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).max().orElse(0);
    }

    public double getMin(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).min().orElse(0);
    }

    public double getSum(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }

    private int getSize(List<Integer> list) {
        return list.size();
    }

}
