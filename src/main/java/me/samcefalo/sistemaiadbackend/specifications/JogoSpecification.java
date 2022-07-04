package me.samcefalo.sistemaiadbackend.specifications;

import lombok.Data;
import me.samcefalo.sistemaiadbackend.models.Atleta;
import me.samcefalo.sistemaiadbackend.models.Equipe;
import me.samcefalo.sistemaiadbackend.models.Jogo;
import me.samcefalo.sistemaiadbackend.specifications.criterias.JogoCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class JogoSpecification implements Specification<Jogo> {

    private JogoCriteria criteria;

    public JogoSpecification(JogoCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Jogo> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicateList = new ArrayList<>();

        if (criteria.getAtletaId() != null) {
            Join<Jogo, Atleta> join = root.join("atletas");
            predicateList.add(builder.equal(join.get("id"), criteria.getAtletaId().intValue()));
        }

        if (criteria.getEquipeId() != null) {
            Join<Jogo, Equipe> join = root.join("equipes");
            predicateList.add(builder.equal(join.get("id"), criteria.getEquipeId().intValue()));
        }

        if (criteria.getUserId() != null) {
            predicateList.add(builder.equal(root.get("user"), criteria.getUserId()));
        }

        if (criteria.getTipo() != null && criteria.getTipo() > 0) {
            predicateList.add(builder.equal(root.get("tipo"), criteria.getTipo()));
        }

        if (criteria.getEsporte() != null && criteria.getEsporte() > 0) {
            predicateList.add(builder.equal(root.get("esporte"), criteria.getEsporte()));
        }

        return builder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }
}
