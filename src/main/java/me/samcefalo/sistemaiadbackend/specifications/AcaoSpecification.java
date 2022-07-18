package me.samcefalo.sistemaiadbackend.specifications;

import lombok.Data;
import me.samcefalo.sistemaiadbackend.models.Acao;
import me.samcefalo.sistemaiadbackend.models.Desarme;
import me.samcefalo.sistemaiadbackend.models.Finalizacao;
import me.samcefalo.sistemaiadbackend.models.Jogo;
import me.samcefalo.sistemaiadbackend.services.exceptions.ObjectNotFoundException;
import me.samcefalo.sistemaiadbackend.services.utils.ClassUtils;
import me.samcefalo.sistemaiadbackend.specifications.criterias.AcaoCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class AcaoSpecification implements Specification<Acao> {

    private ClassUtils classUtils;

    private AcaoCriteria criteria;

    public AcaoSpecification(AcaoCriteria criteria) {
        this.criteria = criteria;
        this.classUtils = new ClassUtils();
    }

    @Override
    public Predicate toPredicate(Root<Acao> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicateList = new ArrayList<>();

        if (!StringUtils.isEmpty(criteria.getCategoria()) && criteria.getCategoria() != null) {
            try {
                Class acaoClass = classUtils.getAcaoClass(criteria.getCategoria());
                predicateList.add(builder.equal(root.type(), acaoClass));
            } catch (ClassNotFoundException e) {
                throw new ObjectNotFoundException("Ação não encontrada. tipo: " + criteria.getCategoria());
            }
        }

        if (criteria.getTempoInsercao() > 0) {
            predicateList.add(builder.equal(root.get("tempoInsercao"), criteria.getTempoInsercao()));
        }

        if (criteria.getData() != null) {
            predicateList.add(builder.equal(root.get("data"), criteria.getData()));
        }

        if (criteria.getAtletaId() != null) {
            predicateList.add(builder.equal(root.get("atleta"), criteria.getAtletaId()));
        }

        if (criteria.getJogoId() != null) {
            predicateList.add(builder.equal(root.get("jogo"), criteria.getJogoId()));
        }

        if (criteria.getEquipeId() != null) {
            predicateList.add(builder.equal(root.get("equipe"), criteria.getEquipeId()));
        }

        if (criteria.getUserId() != null) {
            predicateList.add(builder.equal(root.get("user"), criteria.getUserId()));
        }

        if (!StringUtils.isEmpty(criteria.getPlacar()) && criteria.getPlacar() != null) {
            predicateList.add(builder.equal(root.get("placar"), criteria.getPlacar()));
        }

        if (criteria.getTempo() != null) {
            predicateList.add(builder.equal(root.get("tempo"), criteria.getTempo()));
        }

        if (criteria.getGrauDificuldade() != null && criteria.getGrauDificuldade() > 0) {
            predicateList.add(builder.equal(root.get("grauDificuldade"), criteria.getGrauDificuldade()));
        }

        if (criteria.getArea() != null && criteria.getArea() > 0) {
            predicateList.add(builder.equal(root.get("area"), criteria.getArea()));
        }

        if (criteria.getEtapa() != null && criteria.getEtapa() > 0) {
            predicateList.add(builder.equal(root.get("etapa"), criteria.getGrauDificuldade()));
        }

        if (criteria.getExito() != null) {
            predicateList.add(builder.equal(root.get("exito"), criteria.getExito()));
        }

        if (criteria.getGol() != null) {
            predicateList.add(builder.equal(builder.treat(root, Finalizacao.class).get("gol"), criteria.getGol()));
        }

        if (criteria.getPosseDeBola() != null) {
            predicateList.add(builder.equal(builder.treat(root, Desarme.class).get("posseDeBola"), criteria.getPosseDeBola()));
        }

        if (criteria.getTipoJogo() != null && criteria.getTipoJogo() > 0) {
            Join<Acao, Jogo> join = root.join("jogo");
            predicateList.add(builder.equal(join.get("tipo"), criteria.getTipoJogo()));
        }

        if (criteria.getEsporte() != null && criteria.getEsporte() > 0) {
            Join<Acao, Jogo> join = root.join("jogo");
            predicateList.add(builder.equal(join.get("esporte"), criteria.getEsporte()));
        }

        return builder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }
}
