package me.samcefalo.sistemaiadbackend.specifications;

import lombok.Data;
import me.samcefalo.sistemaiadbackend.models.Acao;
import me.samcefalo.sistemaiadbackend.models.Desarme;
import me.samcefalo.sistemaiadbackend.models.Finalizacao;
import me.samcefalo.sistemaiadbackend.services.exceptions.ObjectNotFoundException;
import me.samcefalo.sistemaiadbackend.services.utils.ClassUtils;
import me.samcefalo.sistemaiadbackend.specifications.criterias.AcaoCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

        if (criteria.getGrauDificuldade() > 0 && criteria.getGrauDificuldade() != null) {
            predicateList.add(builder.equal(root.get("grauDificuldade"), criteria.getGrauDificuldade()));
        }

        if (criteria.getArea() > 0 && criteria.getArea() != null) {
            predicateList.add(builder.equal(root.get("area"), criteria.getArea()));
        }

        if (criteria.getEtapa() > 0 && criteria.getEtapa() != null) {
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

        return builder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }
}
