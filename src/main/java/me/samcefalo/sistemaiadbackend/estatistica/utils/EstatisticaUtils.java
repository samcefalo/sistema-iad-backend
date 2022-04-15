package me.samcefalo.sistemaiadbackend.estatistica.utils;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EstatisticaUtils {

    public double getAvg(List<Integer> list) {
        return list.stream()
                .mapToInt(Integer::intValue)
                .average().orElse(0);
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

    public double getScoreT(double value, List<Integer> list) {
        return 50 + (10 * (getScoreZ(value, list)));
    }

    public double getScoreZ(double value, List<Integer> list) {
        return (value - getAvg(list) / getStandardDeviation(list));
    }

    public double getMax(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).max().orElse(0);
    }

    public double getMin(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).min().orElse(0);
    }

    private int getSize(List<Integer> list) {
        return list.size();
    }

}
