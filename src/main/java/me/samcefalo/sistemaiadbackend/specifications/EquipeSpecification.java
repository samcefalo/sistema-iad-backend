package me.samcefalo.sistemaiadbackend.specifications;

import lombok.Data;
import me.samcefalo.sistemaiadbackend.models.Atleta;
import me.samcefalo.sistemaiadbackend.models.Equipe;
import me.samcefalo.sistemaiadbackend.models.Jogo;
import me.samcefalo.sistemaiadbackend.specifications.criterias.EquipeCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class EquipeSpecification implements Specification<Equipe> {

    private EquipeCriteria criteria;

    public EquipeSpecification(EquipeCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Equipe> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicateList = new ArrayList<>();

        if (criteria.getAtleta() != null) {
            Join<Equipe, Atleta> join = root.join("atletas");
            predicateList.add(builder.equal(join.get("id"), criteria.getAtleta().intValue()));
        }

        if (criteria.getJogo() != null) {
            Join<Equipe, Jogo> join = root.join("jogos");
            predicateList.add(builder.equal(join.get("id"), criteria.getJogo().intValue()));
        }

        if (criteria.getUser() != null) {
            predicateList.add(builder.equal(root.get("user"), criteria.getUser()));
        }

        if (!StringUtils.isEmpty(criteria.getNome()) && criteria.getNome() != null) {
            predicateList.add(builder.equal(root.get("nome"), criteria.getNome()));
        }

        return builder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }
}
