package me.samcefalo.sistemaiadbackend.estatistica;

import java.util.List;

public interface Estatistica {

    double getMedia(List<Class<? extends Integer>> list);

    double getDesvPada();
}
