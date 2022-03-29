package me.samcefalo.sistemaiadbackend.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResourceUtils {

    /**
     * Mapear todos números em uma string e retornar uma lista
     *
     * @param s Linha com números inteiros
     * @return Lista com todos números
     */
    public static List<Integer> decodeIntList(String s) {
        return Arrays.asList(s.split(","))
                .stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    /**
     * Decodificar uma String
     *
     * @param param String a ser decodificado
     * @return String decodificada
     */
    public static String decodeParam(String param) {
        try {
            return URLDecoder.decode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

}
