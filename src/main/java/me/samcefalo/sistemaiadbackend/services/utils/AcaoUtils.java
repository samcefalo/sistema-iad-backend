package me.samcefalo.sistemaiadbackend.services.utils;

import me.samcefalo.sistemaiadbackend.models.Acao;
import me.samcefalo.sistemaiadbackend.services.AcaoService;
import me.samcefalo.sistemaiadbackend.services.EquipeService;
import me.samcefalo.sistemaiadbackend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AcaoUtils {

    @Autowired
    private AcaoService acaoService;
    @Autowired
    private EquipeService equipeService;
    @Autowired
    private ClassUtils classUtil;

    public Class<?> getClass(String categoria) {
        Class<?> categoriaClass;
        try {
            categoriaClass = classUtil.getAcaoClass(categoria);
        } catch (ClassNotFoundException e) {
            throw new ObjectNotFoundException("Acao não encontrada. ID: " + categoria + ", Tipo: " + Acao.class.getSimpleName());
        }
        return categoriaClass;
    }

    public List<Acao> getAcoesGlobal(Class<?> categoria) {
        List<Acao> acoes;
        if (categoria.equals(Acao.class)) {
            acoes = acaoService.findAll();
        } else
            acoes = acaoService.findAllByCategoria(categoria);

        return acoes;
    }

    public List<Acao> getAcoesFromEquipe(int equipeId, Class<?> categoria) {
        List<Acao> acoes;
        if (categoria.equals(Acao.class)) {
            acoes = equipeService.findAcoes(equipeId);
        } else
            acoes = equipeService.findAcoesByCategoria(equipeId, categoria);

        return acoes;
    }
}
